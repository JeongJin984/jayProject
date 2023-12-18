package com.jay.transactserver.data.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transact")
class Transact(
    @Id
    @Column(name = "trans_id")
    var transId : String? = null,
    var reqDt : LocalDateTime,
    var resDt : LocalDateTime,
    @Enumerated(EnumType.STRING)
    var resCode: TransactStatus,
    var description : String,
    var cardNo : String,
    var serviceFee : BigDecimal,
    var totalAmount : BigDecimal,
    var serviceAmount: BigDecimal,
    @Enumerated(EnumType.STRING)
    var transType: TransType
) {
}

enum class TransactStatus(status: String) {
    SALE("0000"), PURCHASE_SUCCESS("0001"),
    SERVER_ERROR("Z000"), PURCHASE_ERROR("X000"),
    REJECT("C000"), CHARGEBACK("V000")
}

enum class TransType {
    REFUND, BUY
}