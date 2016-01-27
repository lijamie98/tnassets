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
public class ImportAwsInstanceLastMonthTest {
	@Autowired
	TnAssetsService service;
	
	@Test
	@Transactional("prodAssetsTransactionManager")
	public void testAll() throws ReflectiveOperationException, TnassetsDataException {
		service.importLastDays(30);
	}
}