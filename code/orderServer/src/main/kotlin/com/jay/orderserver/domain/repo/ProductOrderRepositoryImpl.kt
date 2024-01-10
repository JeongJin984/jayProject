package com.jay.orderserver.domain.repo

import jakarta.persistence.EntityManagerFactory
import org.springframework.stereotype.Repository

@Repository
class ProductOrderRepositoryImpl(
    private val emf: EntityManagerFactory
) : ProductOrderRepositoryCustom {
    private val em = emf.createEntityManager()

    fun getOrderInfo() {
        em.createQuery("select * from Pro")
    }
}