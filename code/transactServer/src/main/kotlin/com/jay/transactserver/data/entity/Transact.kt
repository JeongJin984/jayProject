package com.jay.transactserver.data.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transact")
class Transact(
    @Id
    @Column(name = "trans_id")
    private var transId : String? = null,
    private var reqDt : LocalDateTime,
    private var resDt : LocalDateTime,
    @Enumerated(EnumType.STRING)
    private var resCode: TransactStatus,
    private var description : String,
    private var cardNo : String,
    private var serviceFee : BigDecimal,
    private var totalAmount : BigDecimal,
    private var serviceAmount: BigDecimal,
    @Enumerated(EnumType.STRING)
    private var transType: TransType
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