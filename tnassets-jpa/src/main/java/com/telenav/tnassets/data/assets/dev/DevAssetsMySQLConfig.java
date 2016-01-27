package com.telenav.tnassets.data.assets.dev;

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
@EnableJpaRepositories(entityManagerFactoryRef="devAssetsEntityManagerFactory", transactionManagerRef="devAssetsTransactionManager")
@EnableTransactionManagement
public class DevAssetsMySQLConfig extends BaseMySQLConfig {

	@Bean
	public PlatformTransactionManager devAssetsTransactionManager() {
		return baseTransactionManager(devAssetsEntityManagerFactory());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean devAssetsEntityManagerFactory() {
		String[] packagesToScan = new String[] { this.getClass().getPackage().getName() , "com.telenav.tnassets.data"};
		return baseEntityManagerFactory(devAssetsDataSource(), packagesToScan);
	}
	
	@Bean(destroyMethod = "close")
	public DataSource devAssetsDataSource() {
		return datasource("jdbc:mysql://10.224.0.89:3306/corpasssetsv1?query_cache_size=0");
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor devExceptionTranslation() {
		return baseExceptionTranslation();
	}
}
