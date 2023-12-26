package com.jay.memberserver.infra.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
@EnableJpaRepositories(
        basePackages = "com.jay.memberserver.domain.repo",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
public class DevDBConfig {
    @Bean("datasource")
    public DataSource datasource() {
        return new HikariDataSource(new HikariConfig("/datasource/dev.master.datasource.properties"));
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean entityManager = builder
                .dataSource(datasource())
                .packages("com.jay.memberserver.domain.entity")
                .persistenceUnit("entityManager")
                .build();

        entityManager.setJpaVendorAdapter(adapter);

        return entityManager;
    }

    @Bean("transactionManager")
    PlatformTransactionManager jayMarketTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory factoryBean){
        return new JpaTransactionManager(factoryBean);
    }
}
