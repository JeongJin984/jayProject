package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.OrderLine
import org.springframework.data.jpa.repository.JpaRepository

interface OrderLineRepository : JpaRepository<OrderLine, Long> {
}