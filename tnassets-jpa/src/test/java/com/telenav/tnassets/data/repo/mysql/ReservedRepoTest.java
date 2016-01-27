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

import com.telenav.tnassets.data.ReservedEntity;
import com.telenav.tnassets.data.assets.prod.ProdAssetsMySQLConfig;
import com.telenav.tnassets.data.assets.prod.ProdReservedRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProdAssetsMySQLConfig.class })
public class ReservedRepoTest {
	@Autowired
	ProdReservedRepo reservedRepo;

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
		List<ReservedEntity> res = reservedRepo.findByDateAfter(dt);
		for (ReservedEntity re : res) {
			System.out.println(re);
		}
	}
}


