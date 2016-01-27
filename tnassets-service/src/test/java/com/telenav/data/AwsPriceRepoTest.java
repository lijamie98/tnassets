package com.telenav.data;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.telenav.tnassets.data.StatusEntity;
import com.telenav.tnassets.data.assets.prod.ProdAssetsMySQLConfig;
import com.telenav.tnassets.data.assets.prod.ProdStatusRepoMySQL;
import com.telenav.tnassets.data.awsprices.AwsPriceEntityMySQL;
import com.telenav.tnassets.data.awsprices.AwsPriceMySQLConfig;
import com.telenav.tnassets.data.awsprices.AwsPriceRepoMySQL;
import com.telenav.tnassets.entity.AwsPriceEntityES;
import com.telenav.tnassets.repo.AwsPriceRepoES;
import com.telenav.tnassets.rocketflow.util.BeanUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ProdAssetsMySQLConfig.class, AwsPriceMySQLConfig.class})
public class AwsPriceRepoTest {
	@Autowired 
	AwsPriceRepoMySQL awspriceRepo;
	
	@Autowired
	AwsPriceRepoES awspriceRepoES;
	
	@Autowired
	ProdStatusRepoMySQL statusRepo;
	
	@Test
	@Transactional("awspriceTransactionManager")
	public void testAll() throws ReflectiveOperationException, ParseException {
		StatusEntity se = statusRepo.findFirstByOrderByDatetimeDesc();
		
		System.out.println(se.getDatetime());
		
		List<AwsPriceEntityMySQL> ins = awspriceRepo.findByDatetime(se.getDatetime());
		List<AwsPriceEntityES> ieess = new ArrayList<AwsPriceEntityES>(ins.size());
		System.out.println(ins.size() + " to be written.");
		for (AwsPriceEntityMySQL in : ins) {
			AwsPriceEntityES iees = BeanUtil.convert(in, AwsPriceEntityES.class);
			iees.setId(in.getType() + "-" + in.getRegion() + "-" + in.getSize() + "-" + in.getDatetime().getTime());
			if (in.getPrice().startsWith("N")) {
				iees.setPrice("0");
			}
			ieess.add(iees);
			if (ieess.size() > 1000) {
//				awspriceRepoES.save(ieess);
				System.out.println("Saving " + ieess.size() + " records.");
				ieess.clear();
			}
			System.out.println(in);
		}
		
		if (ieess.size() > 0) {
//			awspriceRepoES.save(ieess);
			System.out.println("Saving " + ieess.size() + " records.");
		}
		
	}
}
