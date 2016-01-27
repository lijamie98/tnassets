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

import com.telenav.tnassets.data.InstanceEntityMySQL;
import com.telenav.tnassets.data.assets.prod.ProdAssetsMySQLConfig;
import com.telenav.tnassets.data.assets.prod.ProdInstanceRepoMySQL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProdAssetsMySQLConfig.class })
public class InstanceRepoTest {
	@Autowired
	ProdInstanceRepoMySQL connectionRepo;

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@Test
	@Transactional("prodAssetsTransactionManager")
	public void testAll() {
		Date dt = new Date(System.currentTimeMillis() - 2 * 3600 * 1000);
		System.out.println(dt);
		List<InstanceEntityMySQL> ins = connectionRepo.findByDateAfter(dt);
		for (InstanceEntityMySQL in : ins) {
			System.out.println(in);
		}
	}

}
