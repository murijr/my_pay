package com.mofaia.mypay.app.data.entity

import com.google.firebase.Timestamp
import java.math.BigDecimal

data class Transaction(var value: BigDecimal? = null, var type: String? = null
                       , var createAt: Timestamp = Timestamp.now()) {
    companion object {
        const val FIELD_VALUE = "value"
        const val FIELD_TYPE = "type"
        const val FIELD_CREATE_AT = "createAt"
    }

    enum class Type {
        BRITA_CREDIT, BITCOIN_CREDIT, BRL_CREDIT, BRITA_DEBIT,
        BITCOIN_DEBIT, BRL_DEBIT()

    }
}