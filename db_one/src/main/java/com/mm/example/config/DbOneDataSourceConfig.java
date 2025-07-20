package com.mm.example.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.mm.example.repository",
        entityManagerFactoryRef = "dbOneEntityManagerFactory",
        transactionManagerRef = "dbOneTransactionManager")
public class DbOneDataSourceConfig {

    @Primary
    @Bean(name = "dbOneDataSourceProperties")
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties dbOneDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "dbOneDataSource")
    public DataSource dbOneDataSource(@Qualifier("dbOneDataSourceProperties") DataSourceProperties dbOneDataSourceProperties) {
        return dbOneDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "dbOneEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dbOneEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("dbOneDataSource") DataSource dbOneDataSource){
        return builder
                .dataSource(dbOneDataSource)
                .packages("com.mm.example.model") // Specify the package where your entities are located
                .persistenceUnit("dbOne")
                .build();
    }


    @Primary
    @Bean(name = "dbOneTransactionManager")
    public JpaTransactionManager dbOneTransactionManager(
            @Qualifier("dbOneEntityManagerFactory") EntityManagerFactory dbOneEntityManagerFactory) {
        return new JpaTransactionManager(dbOneEntityManagerFactory);
    }
}
