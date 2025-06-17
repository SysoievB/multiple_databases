package com.multiple_databases.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement//Enables support for managing transactions using annotations like @Transactional. This is necessary for Spring to manage commit/rollback
@EnableJpaRepositories(//This tells Spring Data where to find your repositories and how to configure them.
        basePackages = "com.multiple_databases.repo.client",//All repository interfaces for MySQL are here.
        entityManagerFactoryRef = "mysqlEntityManager",//Tells Spring to use the specified EntityManager for this repo package.
        transactionManagerRef = "mysqlTransactionManager"//Tells Spring to use this transaction manager for this repo package.
)
public class MySQLConfig {

    //Creates a DataSource (i.e., DB connection settings: URL, username, password).
    @Bean
    @Primary//This will be the default if multiple data sources exist.
    @ConfigurationProperties(prefix = "spring.datasource.mysql")//Uses spring.datasource.mysql.* from application.properties.
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mysqlEntityManager(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(mysqlDataSource())
                .packages("com.multiple_databases.entity.client")//Scans com.example.mysql.model for entities.
                .persistenceUnit("mysql")//Defines a persistence unit name ("mysql") — useful when multiple databases are in play.
                .build();
    }

    //Enables transaction management (commit/rollback) for MySQL operations.
    @Bean
    @Primary
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManager") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}
