package com.mofaia.mypay.app.data.entity

import com.google.firebase.Timestamp
import java.math.BigDecimal

data class Transaction(var value: BigDecimal? = null, var type: String? = null
                       , var createAt: Timestamp = Timestamp.now()) {
    companion object {

        const val FIELD_VALUE = "value"
        const val FIELD_TYPE = "type"
        const val FIELD_CREATE_AT = "createAt"

        const val TRANSACTION_TYPE_BRITA_WALLET_CREDIT = "credit_brita"
        const val TRANSACTION_TYPE_BITCOIN_WALLET_CREDIT = "credit_bitcoin"
        const val TRANSACTION_TYPE_BRL_WALLET_CREDIT = "credit_brl"

        const val TRANSACTION_TYPE_BRITA_WALLET_DEBIT = "debit_brita"
        const val TRANSACTION_TYPE_BITCOIN_WALLET_DEBIT = "debit_bitcoin"
        const val TRANSACTION_TYPE_BRL_WALLET_DEBIT = "debit_brl"
    }
}