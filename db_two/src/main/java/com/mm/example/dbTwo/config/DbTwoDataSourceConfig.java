package com.mm.example.dbTwo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.mm.example.dbTwo.repository",
        entityManagerFactoryRef = "dbTwoEntityManagerFactory",
        transactionManagerRef = "dbTwoTransactionManager"
)
public class DbTwoDataSourceConfig {


    @Bean(name = "dbTwoDataSourceProperties")
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSourceProperties dbTwoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dbTwoDataSource")
    public DataSource dbTwoDataSource(@Qualifier("dbTwoDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "dbTwoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dbTwoEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("dbTwoDataSource") DataSource dbTwoDataSource) {

        return builder
                .dataSource(dbTwoDataSource)
                .packages("com.mm.example.dbTwo.model")
                .persistenceUnit("dbTwo")
                .build();
    }

    @Bean(name = "dbTwoTransactionManager")
    public JpaTransactionManager dbTwoTransactionManager(
            @Qualifier("dbTwoEntityManagerFactory") EntityManagerFactory dbTwoEntityManagerFactory) {
        return new JpaTransactionManager(dbTwoEntityManagerFactory);
    }


}
