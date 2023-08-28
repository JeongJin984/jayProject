package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.OrderList
import org.springframework.data.jpa.repository.JpaRepository

interface OrderListRepository : JpaRepository<OrderList, Long> {
}