package com.geomark.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * JPA Spring Data configuration
 * 
 * @author Georgios Markakis
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = {
		"com.geomark.dao"
})
@EnableTransactionManagement
public class JPAConfig {


	/**
	 * LOGGER
	 */
	Logger logger = LoggerFactory.getLogger(JPAConfig.class);


	/**
	 * JPA Entity Manager Configuration
	 *
	 * @param dataSource
	 * @return
	 */
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		Properties jpaProperties = new Properties();
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.geomark");
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
		localContainerEntityManagerFactoryBean.afterPropertiesSet();

		logger.debug("JPA Properties: " + jpaProperties.toString());

		return localContainerEntityManagerFactoryBean.getObject();
	}

	/**
	 * Transaction Manager Configuration
	 *
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}
}
