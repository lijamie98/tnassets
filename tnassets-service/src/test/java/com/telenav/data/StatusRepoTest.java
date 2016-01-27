package com.telenav.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.telenav.tnassets.data.StatusEntity;
import com.telenav.tnassets.data.assets.prod.ProdAssetsMySQLConfig;
import com.telenav.tnassets.data.assets.prod.ProdStatusRepoMySQL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ProdAssetsMySQLConfig.class})
public class StatusRepoTest {
	@Autowired
	ProdStatusRepoMySQL statusRepo;

	@Test
	@Transactional("prodAssetsTransactionManager")
	public void testAll() throws ReflectiveOperationException {
		StatusEntity se = statusRepo.findFirstByOrderByDatetimeDesc();

		System.out.println(se);
	}
}
