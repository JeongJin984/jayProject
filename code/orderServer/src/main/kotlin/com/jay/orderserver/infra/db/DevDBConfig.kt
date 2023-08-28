package com.jay.orderserver.infra.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@Profile("dev")
@EnableJpaRepositories(
    basePackages = ["com.jay.orderserver.domain.jayMarket.repo"],
    entityManagerFactoryRef = "jayMarketEntityManagerFactory",
    transactionManagerRef = "jayMarketTransactionManager"
)
class DevDBConfig {
    @Bean("jayMarketDatasource")
    fun datasource(): DataSource {
        return HikariDataSource(HikariConfig("/datasource/dev.master.datasource.properties"))
    }

    @Bean("jayMarketEntityManagerFactory")
    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val adaptor = HibernateJpaVendorAdapter()
        adaptor.setShowSql(true)
        adaptor.setGenerateDdl(true)
        adaptor.setGenerateDdl(false)

        val entityManagerFactoryBean = builder
            .dataSource(datasource())
            .packages("com.jay.orderserver.domain.jayMarket.entity")
            .persistenceUnit("jayMarketEntityManager")
            .build()

        entityManagerFactoryBean
            .jpaVendorAdapter = adaptor

        return entityManagerFactoryBean
    }

    @Bean("jayMarketTransactionManager")
    fun jayMarketTransactionManager(
        @Qualifier("jayMarketEntityManagerFactory") factoryBean: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager {
        return JpaTransactionManager(factoryBean.`object`!!)
    }
}