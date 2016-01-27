package com.telenav.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.telenav.tnassets.elastic.TnassetsDataException;
import com.telenav.tnassets.service.TnAssetsService;
import com.telenav.tnassets.service.TnAssetsServiceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TnAssetsServiceConfig.class})
public class ImportAwsInstanceTest {
	@Autowired
	TnAssetsService service;
	
	@Test
	@Transactional("prodAssetsTransactionManager")
	public void testProd() throws ReflectiveOperationException, TnassetsDataException {
		service.importProdInstances();
	}
	
	
	@Test
	@Transactional("devAssetsTransactionManager")
	public void testDev() throws ReflectiveOperationException, TnassetsDataException {
		service.importDevInstances();
	}
	
}


