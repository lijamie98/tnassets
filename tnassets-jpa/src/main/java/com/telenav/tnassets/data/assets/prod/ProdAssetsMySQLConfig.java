package com.telenav.tnassets.data.assets.prod;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.telenav.tnassets.data.BaseMySQLConfig;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef="prodAssetsEntityManagerFactory", transactionManagerRef="prodAssetsTransactionManager")
@EnableTransactionManagement
public class ProdAssetsMySQLConfig extends BaseMySQLConfig {

	@Bean
	public PlatformTransactionManager prodAssetsTransactionManager() {
		return baseTransactionManager(prodAssetsEntityManagerFactory());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean prodAssetsEntityManagerFactory() {
		String[] packagesToScan = new String[] { this.getClass().getPackage().getName() , "com.telenav.tnassets.data"};
		return baseEntityManagerFactory(prodAssetsDataSource(), packagesToScan);
	}
	
	@Bean(destroyMethod = "close")
	public DataSource prodAssetsDataSource() {
		return datasource("jdbc:mysql://10.224.0.89:3306/asssetsv1?query_cache_size=0");
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor prodExceptionTranslation() {
		return baseExceptionTranslation();
	}
}
