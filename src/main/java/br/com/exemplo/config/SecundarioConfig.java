package br.com.exemplo.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SecundarioConfig {

	@Bean
	@ConfigurationProperties("secundario.datasource")
	public DataSource secundarioDataSource() {
	    return DataSourceBuilder.create()
	            .type(com.zaxxer.hikari.HikariDataSource.class)
	            .build();
	}


	@Bean
	@ConfigurationProperties("secundario.jpa")
	public SecundarioJpaProperties secundarioJpaProperties() {
	    return new SecundarioJpaProperties();
	}

	@Bean(name = "secundarioEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secundarioEntityManagerFactory(
	        EntityManagerFactoryBuilder builder,
	        @Qualifier("secundarioDataSource") DataSource dataSource,
	        @Qualifier("secundarioJpaProperties") SecundarioJpaProperties jpaProps) {

	    return builder
	            .dataSource(dataSource)
	            .packages("br.com.exemplo.entidade.secundario")
	            .persistenceUnit("secundario")
	            .properties(jpaProps.getProperties()) // 👈 aqui ele injeta
	            .build();
	}



    @Bean(name = "secundarioTransactionManager")
    public PlatformTransactionManager secundarioTransactionManager(
            @Qualifier("secundarioEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
    
    
    
    

}
