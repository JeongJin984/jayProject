package com.jay.orderserver.domain.jayMarket.repo

import com.jay.orderserver.domain.jayMarket.entity.OrderList
import org.springframework.data.jpa.repository.JpaRepository

interface OrderListRepository : JpaRepository<OrderList, Long> {
}