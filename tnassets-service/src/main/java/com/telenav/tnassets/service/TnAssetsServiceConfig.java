package com.telenav.tnassets.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.telenav.tnassets.data.assets.dev.DevAssetsMySQLConfig;
import com.telenav.tnassets.data.assets.prod.ProdAssetsMySQLConfig;
import com.telenav.tnassets.data.awsprices.AwsPriceMySQLConfig;
import com.telenav.tnassets.elastic.ElasticSearchManager;
import com.telenav.tnassets.repo.InstanceRepoES;

@Configuration
@Import({ AwsPriceMySQLConfig.class, ProdAssetsMySQLConfig.class, DevAssetsMySQLConfig.class})
public class TnAssetsServiceConfig {

	@Bean
	public TnAssetsService tnassetsService() {
		TnAssetsService tnassetsService = new TnAssetsService();
		tnassetsService.start();
		return tnassetsService;
	}
	
	@Bean 
	public InstanceRepoES instanceRepoES() {
		InstanceRepoES repo = new InstanceRepoES();
		repo.setElasticSearchManager(elasticSearchManager());
		
		return repo;
	}
	
	@Bean
	public ElasticSearchManager elasticSearchManager() {
		return new ElasticSearchManager();
	}
}
