package com.jay.orderserver.business.service

import com.jay.orderserver.common.dto.ClientOrderShippingInfoDTO
import com.jay.orderserver.domain.entity.Address
import com.jay.orderserver.domain.entity.Receiver
import com.jay.orderserver.domain.entity.ShippingInfo
import com.jay.orderserver.domain.repo.UserOrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DeliverService(
    private val userOrderRepository: UserOrderRepository
) {
    @Transactional
    fun changeShippingInfo(shippingInfo: ClientOrderShippingInfoDTO) {
        val userOrder = userOrderRepository.findById(shippingInfo.userOrderId).get()
        userOrder.changeShippingInfo(ShippingInfo(
            shippingInfoId = null,
            receiver = Receiver(
                name = shippingInfo.receiver,
                phone = shippingInfo.receiverPhoneNum
            ),
            address = Address(
                zipCode = shippingInfo.zipCode,
                address1 = shippingInfo.address1,
                address2 = shippingInfo.address2
            )
        ))
    }
}