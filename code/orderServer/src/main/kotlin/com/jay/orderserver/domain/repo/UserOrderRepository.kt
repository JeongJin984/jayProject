package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.UserOrder
import org.springframework.data.jpa.repository.JpaRepository

interface UserOrderRepository : JpaRepository<UserOrder, Long?> {
}