package com.jay.orderserver.domain.jayMarket.repo

import com.jay.orderserver.domain.jayMarket.entity.ShippingInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ShippingInfoRepository : JpaRepository<ShippingInfo, Long> {
}