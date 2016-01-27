package com.telenav.tnassets.data.awsprices;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef="awspriceEntityManagerFactory", transactionManagerRef="awspriceTransactionManager")
@EnableTransactionManagement
public class AwsPriceMySQLConfig {

	@Bean
	public PlatformTransactionManager awspriceTransactionManager() {
		return new JpaTransactionManager(awspriceEntityManagerFactory().getObject());
	}
	

	@Bean
	public LocalContainerEntityManagerFactoryBean awspriceEntityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(awspriceDataSource());
		em.setJpaVendorAdapter(vendorAdapter);
		em.setPackagesToScan(new String[] { this.getClass().getPackage().getName() });

		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	@Bean(destroyMethod = "close")
	DataSource awspriceDataSource() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("root");
		ds.setPassword("mypna123");
		ds.setUrl("jdbc:mysql://10.224.0.89:3306/awsprices?query_cache_size=0");
		ds.setInitialSize(10);
		ds.setMaxIdle(20);
		ds.setMaxActive(20);
		ds.setTestWhileIdle(true);
		ds.setValidationQuery("SELECT 1");
		ds.setTestOnBorrow(true);

		return ds;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "false");
		return properties;
	}

}
