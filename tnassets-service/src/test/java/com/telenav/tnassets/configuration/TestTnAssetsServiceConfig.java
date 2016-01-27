package com.telenav.tnassets.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.telenav.tnassets.data.assets.prod.ProdAssetsMySQLConfig;
import com.telenav.tnassets.data.awsprices.AwsPriceMySQLConfig;
import com.telenav.tnassets.service.TnAssetsService;

@Configuration
@Import({  AwsPriceMySQLConfig.class, ProdAssetsMySQLConfig.class})
public class TestTnAssetsServiceConfig {

	@Bean
	public TnAssetsService tnassetsService() {
		TnAssetsService tnassetsService = new TnAssetsService();
		// Do not start in JUnit Test.
		// tnassetsService.start();
		return tnassetsService;
	}
}
