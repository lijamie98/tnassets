package com.telenav.tnassets.data.repo.mysql;


import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.telenav.tnassets.data.awsprices.AwsPriceEntityMySQL;
import com.telenav.tnassets.data.awsprices.AwsPriceMySQLConfig;
import com.telenav.tnassets.data.awsprices.AwsPriceRepoMySQL;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AwsPriceMySQLConfig.class})
public class AwsPriceRepoTest {
	@Autowired 
	AwsPriceRepoMySQL awsPriceRepo;
	
	@Before
	public void setup() {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	@Transactional("awspriceTransactionManager")
	public void testAll() {
		Date dt = new Date(System.currentTimeMillis() - 1 * 3600 * 1000);
		System.out.println(dt);
		List<AwsPriceEntityMySQL> prices = awsPriceRepo.findByDateAfter(dt);
		for (AwsPriceEntityMySQL price : prices) {
			System.out.println(price);
		}

	}
}

