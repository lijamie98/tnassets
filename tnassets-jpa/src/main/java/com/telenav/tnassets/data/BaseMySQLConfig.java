package com.telenav.tnassets.data;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

public class BaseMySQLConfig {

	protected PlatformTransactionManager baseTransactionManager(LocalContainerEntityManagerFactoryBean factory) {
		return new JpaTransactionManager(factory.getObject());
	}
	
	protected LocalContainerEntityManagerFactoryBean baseEntityManagerFactory(DataSource datasource, String[] packagesToScan) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(datasource);
		em.setJpaVendorAdapter(vendorAdapter);
		em.setPackagesToScan(packagesToScan);

		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	protected DataSource datasource(String url) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("mypna123");
		ds.setUrl(url);
		ds.setInitialSize(10);
		ds.setMaxIdle(20);
		ds.setMaxActive(20);
		ds.setTestWhileIdle(true);
		ds.setValidationQuery("SELECT 1");
		ds.setTestOnBorrow(true);

		return ds;
	}

	protected PersistenceExceptionTranslationPostProcessor baseExceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "false");
		return properties;
	}
}
