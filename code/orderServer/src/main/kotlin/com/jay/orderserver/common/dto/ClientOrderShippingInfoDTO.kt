package com.jay.orderserver.common.dto

data class ClientOrderShippingInfoDTO(
    var userOrderId: Long,

    var receiver: String,
    var receiverPhoneNum: String,

    var zipCode: String,
    var address1: String,
    var address2: String
) {}
