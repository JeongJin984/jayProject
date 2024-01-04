package com.jay.productserver.infra.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
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
@EnableJpaRepositories(
    basePackages = ["com.jay.productserver.domain.repo"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class DevDBConfig {
    @Bean("datasource")
    @Profile("dev")
    fun datasource(): DataSource {
        return HikariDataSource(HikariConfig("/datasource/dev.master.datasource.properties"))
    }

    @Bean("entityManagerFactory")
    @Profile("dev")
    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
        val adaptor = HibernateJpaVendorAdapter()
        adaptor.setShowSql(true)
        adaptor.setGenerateDdl(true)

        val entityManagerFactoryBean = builder
            .dataSource(datasource())
            .packages("com.jay.productserver.domain.entity")
            .persistenceUnit("entityManager")
            .build()

        entityManagerFactoryBean
            .jpaVendorAdapter = adaptor

        return entityManagerFactoryBean
    }

    @Bean("transactionManager")
    @Profile("dev")
    fun jayMarketTransactionManager(
        @Qualifier("entityManagerFactory") factoryBean: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager {
        return JpaTransactionManager(factoryBean.`object`!!)
    }
}