package com.telenav.tnassets.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.telenav.tnassets.data.InstanceEntityMySQL;
import com.telenav.tnassets.data.ReservedEntity;
import com.telenav.tnassets.data.StatusEntity;
import com.telenav.tnassets.data.assets.dev.DevInstanceRepoMySQL;
import com.telenav.tnassets.data.assets.dev.DevReservedRepo;
import com.telenav.tnassets.data.assets.dev.DevStatusRepoMySQL;
import com.telenav.tnassets.data.assets.prod.ProdInstanceRepoMySQL;
import com.telenav.tnassets.data.assets.prod.ProdReservedRepo;
import com.telenav.tnassets.data.assets.prod.ProdStatusRepoMySQL;
import com.telenav.tnassets.data.awsprices.AwsPriceEntityMySQL;
import com.telenav.tnassets.data.awsprices.AwsPriceRepoMySQL;
import com.telenav.tnassets.elastic.TnassetsDataException;
import com.telenav.tnassets.entity.InstanceEntityES;
import com.telenav.tnassets.repo.InstanceRepoES;
import com.telenav.tnassets.util.BeanUtil;

import it.sauronsoftware.cron4j.Scheduler;

public class TnAssetsService {
	private static Logger logger = Logger.getLogger(TnAssetsService.class);

	@Autowired
	private ProdInstanceRepoMySQL prodInstanceRepo;

	@Autowired
	private ProdStatusRepoMySQL prodStatusRepo;

	@Autowired
	private ProdReservedRepo prodReservedRepo;

	@Autowired
	private DevInstanceRepoMySQL devInstanceRepo;

	@Autowired
	private DevStatusRepoMySQL devStatusRepo;

	@Autowired
	private DevReservedRepo devReservedRepo;

	@Autowired
	private InstanceRepoES instanceRepoES;

	@Autowired
	private AwsPriceRepoMySQL awspriceRepo;

	private boolean started = false;

	@PostConstruct
	public void init() {
	}

	private Scheduler scheduler = new Scheduler();

	public void start() {
		synchronized (this) {
			if (started) {
				return;
			}

			started = true;
			scheduler.schedule("20 * * * *", new Runnable() {
				@Override
				public void run() {
					importProdInstances();
					importDevInstances();
				}
			});

			scheduler.start();
			logger.info("TnAssetsService started ...");
		}
	}

	public void importLastDays(int days) {
		Calendar c = Calendar.getInstance();
		for (int i = 0; i < days; i++) {
			for (int j = 0; j < 24; j ++) {
				Date endDate = c.getTime();
				c.add(Calendar.HOUR_OF_DAY, -1);
				Date startDate = c.getTime();
	
				Map<String, AwsPriceEntityMySQL> hmAwsPrice = updateAwsPrice();
				Map<String, PriceRecord> hmReserved = getReservedMap(hmAwsPrice, getProdReserved());
	
				List<InstanceEntityMySQL> ins = prodInstanceRepo.findByDateBetween(startDate, endDate);
				System.out.println("Saving prod.... startDate=" + startDate + " endDate=" + endDate);
				persist(hmAwsPrice, hmReserved, ins, "prod");
			}
		}

		c = Calendar.getInstance();
		
		for (int i = 0; i < days; i++) {
			for (int j = 0; j < 24; j ++) {
				Date endDate = c.getTime();
				c.add(Calendar.HOUR_OF_DAY, -1);
				Date startDate = c.getTime();
	
				Map<String, AwsPriceEntityMySQL> hmAwsPrice = updateAwsPrice();
				Map<String, PriceRecord> hmReserved = getReservedMap(hmAwsPrice, getDevReserved());
	
				List<InstanceEntityMySQL> ins = devInstanceRepo.findByDateBetween(startDate, endDate);
				System.out.println("Saving dev.... startDate=" + startDate + " endDate=" + endDate);
				persist(hmAwsPrice, hmReserved, ins, "dev");
			}
		}
	}

	private void persist(Map<String, AwsPriceEntityMySQL> hmAwsPrice, Map<String, PriceRecord> hmReserved, List<InstanceEntityMySQL> ins, String account) {
		// pass-1, we calculate the average price of each (size+AZ) combination
		for (InstanceEntityMySQL in : ins) {
			String key = PriceRecord.getKey(in.getSize(), in.getAz());
			PriceRecord pr = hmReserved.get(key);
			
			if (pr != null) {
				pr.incrementInstance();
			}
		}	
		
		for (Entry<String, PriceRecord> entry : hmReserved.entrySet()) {
			System.out.println(entry);
		}
		
		
		// pass-2, we fill the prices
		List<InstanceEntityES> ieess = new ArrayList<InstanceEntityES>(ins.size());
		int count = 0;
		for (InstanceEntityMySQL in : ins) {
			InstanceEntityES iees;
			try {
				count++;
				iees = BeanUtil.convert(in, InstanceEntityES.class);
				iees.setId(in.getInstanceid() + "-" + in.getDate().getTime());
				iees.setAccount(account);

				String key = PriceRecord.getKey(in.getSize(), in.getAz());
				PriceRecord pr = hmReserved.get(key);
				iees.setDedicatedPrice(getAwsPrice(hmAwsPrice, "instances", in.getSize(), in.getAz()));
				iees.setReservedPrice(getAwsPrice(hmAwsPrice, "reserved", in.getSize(), in.getAz()));
				if (pr == null) {
					iees.setAveragePrice(iees.getDedicatedPrice());
				} else {
					iees.setAveragePrice(pr.getAveragePrice());
				}

				ieess.add(iees);
				if (ieess.size() > 1000) {
					logger.debug(ieess.size() + " instances imported.");
					instanceRepoES.save(ieess);
					ieess.clear();
				}
			} catch (ReflectiveOperationException e) {
				// skip this instance
			} catch (TnassetsDataException e) {
				// skip the batch
				ieess.clear();
			}
		}

		if (ieess.size() > 0) {
			try {
				instanceRepoES.save(ieess);
			} catch (TnassetsDataException e) {
				logger.error("Error writing " + ieess.size() + " instances.");
			}
		}

		logger.info("Total " + count + " instances imported.");

	}

	private double getAwsPrice(Map<String, AwsPriceEntityMySQL> hmAwsPrice, String type, String size, String az) {
		AwsPriceEntityMySQL ap = hmAwsPrice.get(type + size + az);
		if (ap == null) 
			return 0.0;
		return Double.parseDouble(ap.getPrice());
	}	
	
	/*
	private void persist2(List<InstanceEntityMySQL> ins, String account) {
		List<InstanceEntityES> ieess = new ArrayList<InstanceEntityES>(ins.size());
		int count = 0;
		for (InstanceEntityMySQL in : ins) {
			InstanceEntityES iees;
			try {
				count++;
				iees = BeanUtil.convert(in, InstanceEntityES.class);
				iees.setId(in.getInstanceid() + "-" + in.getDate().getTime());
				iees.setAccount(account);

				iees.setAwsPrice(getPrice(iees));

				ieess.add(iees);
				if (ieess.size() > 1000) {
					logger.debug(ieess.size() + " instances imported.");
					instanceRepoES.save(ieess);
					ieess.clear();
				}
			} catch (ReflectiveOperationException e) {
				// skip this instance
			} catch (TnassetsDataException e) {
				// skip the batch
				ieess.clear();
			}
		}

		if (ieess.size() > 0) {
			try {
				instanceRepoES.save(ieess);
			} catch (TnassetsDataException e) {
				logger.error("Error writing " + ieess.size() + " instances.");
			}
		}

		logger.info("Total " + count + " instances imported.");

	}
*/
	
//	private float getPrice(InstanceEntityES iees) {
//		AwsPriceEntityMySQL ape = null;
//		PriceRecord rr = hmReserved.get(iees.getType() + iees.getAz());
//		String pricingType = "instances";
//		
//		if (rr != null) {
//			if (rr.consume()) {
//				pricingType = "reserved";
//			} else {
//				pricingType = "instances";
//			}
//		}
//		
//		if (iees.getType() != null) {
//			String key = pricingType + iees.getType() + iees.getAz();
//			ape = hmPrice.get(key);
//		}
//
//		if (ape == null) {
//			logger.debug("missing aws price:" + iees);
//			return 0;
//		} else {
//			try {
//				
//				return (Float.parseFloat(ape.getPrice()));
//			} catch (NumberFormatException nfex) {
//				logger.debug("missing aws price (nfex):" + iees);
//			}
//		}
//		return 0;
//	}

	private Map<String, AwsPriceEntityMySQL> updateAwsPrice() {
		StatusEntity se = prodStatusRepo.findFirstByOrderByDatetimeDesc();
		List<AwsPriceEntityMySQL> awsPrices = awspriceRepo.findByDatetime(se.getDatetime());

		Map<String, AwsPriceEntityMySQL> hmPrice = new HashMap<String, AwsPriceEntityMySQL>();
		for (AwsPriceEntityMySQL ap : awsPrices) {
			hmPrice.put(ap.getType() + ap.getSize() + ap.getRegion(), ap);
			hmPrice.put(ap.getType() + ap.getSize() + ap.getRegion() + "a", ap);
			hmPrice.put(ap.getType() + ap.getSize() + ap.getRegion() + "b", ap);
			hmPrice.put(ap.getType() + ap.getSize() + ap.getRegion() + "c", ap);
			hmPrice.put(ap.getType() + ap.getSize() + ap.getRegion() + "d", ap);
		}

		return hmPrice;
	}
	
	private Map<String, PriceRecord> getReservedMap(Map<String, AwsPriceEntityMySQL> hmAwsPrice, List<ReservedEntity> res) {
		Map<String, PriceRecord> result = new HashMap<String, PriceRecord>();
		
		for (ReservedEntity re : res) {
			PriceRecord record = new PriceRecord(re.getSize(), re.getAz(), Integer.parseInt(re.getCount()));
			double price = getAwsPrice(hmAwsPrice, "reserved", re.getSize(), re.getAz());
			record.setReservedPrice(price);
			price = getAwsPrice(hmAwsPrice, "instances", re.getSize(), re.getAz());
			record.setDedicatedPrice(price);
			
			result.put(record.getKey(), record);
		}
		
		return result;
	}

	private List<ReservedEntity> getProdReserved() {
		StatusEntity se = prodStatusRepo.findFirstByOrderByDatetimeDesc();
		return prodReservedRepo.findByDate(se.getDatetime());
	}

	private List<ReservedEntity> getDevReserved() {
		StatusEntity se = prodStatusRepo.findFirstByOrderByDatetimeDesc();
		return devReservedRepo.findByDate(se.getDatetime());
	}

	public void importProdInstances() {
		logger.info("updating aws price... ");
		logger.info("Importing prod assets ... ");
		StatusEntity se = prodStatusRepo.findFirstByOrderByDatetimeDesc();
		List<InstanceEntityMySQL> ins = prodInstanceRepo.findByDate(se.getDatetime());

		Map<String, AwsPriceEntityMySQL> hmAwsPrice = updateAwsPrice();
		Map<String, PriceRecord> hmReserved = getReservedMap(hmAwsPrice, getProdReserved());
		persist(hmAwsPrice, hmReserved, ins, "prod");
	}

	public void importDevInstances() {
		logger.info("updating aws price... ");
		logger.info("Importing dev assets ... ");
		StatusEntity se = devStatusRepo.findFirstByOrderByDatetimeDesc();
		List<InstanceEntityMySQL> ins = devInstanceRepo.findByDate(se.getDatetime());

		Map<String, AwsPriceEntityMySQL> hmAwsPrice = updateAwsPrice();
		Map<String, PriceRecord> hmReserved = getReservedMap(hmAwsPrice, getProdReserved());
		persist(hmAwsPrice, hmReserved, ins, "dev");
	}
}
