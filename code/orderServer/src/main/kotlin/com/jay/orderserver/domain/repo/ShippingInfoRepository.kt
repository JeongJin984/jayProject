package com.jay.orderserver.domain.repo

import com.jay.orderserver.domain.entity.ShippingInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ShippingInfoRepository : JpaRepository<ShippingInfo, Long> {
}