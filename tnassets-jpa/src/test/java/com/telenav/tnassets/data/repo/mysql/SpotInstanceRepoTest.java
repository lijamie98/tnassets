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

import com.telenav.tnassets.data.SpotInstanceEntity;
import com.telenav.tnassets.data.assets.prod.ProdAssetsMySQLConfig;
import com.telenav.tnassets.data.assets.prod.ProdSpotInstanceRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProdAssetsMySQLConfig.class })
public class SpotInstanceRepoTest {
	@Autowired
	ProdSpotInstanceRepo spotInstanceRepo;

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@Test
	@Transactional("prodAssetsTransactionManager")
	public void testAll() {
		Date dt = new Date(System.currentTimeMillis() - 60 * 24 * 3600 * 1000);
		System.out.println(dt);
		List<SpotInstanceEntity> ins = spotInstanceRepo.findByDateAfter(dt);
//		List<SpotInstanceEntity> ins = spotInstanceRepo.findAll();
		for (SpotInstanceEntity in : ins) {
			System.out.println(in);
		}
	}

}
