package com.mofaia.mypay.app.data.entity

import com.google.firebase.Timestamp

data class Transaction(var value: Double? = null, var type: String? = null, var createAt: Timestamp = Timestamp.now()) {
    companion object {
        const val TRNSACTION_TYPE_BRITA_WALLET_CREDIT = "credit_brita"
        const val TRNSACTION_TYPE_BITCOIN_WALLET_CREDIT = "credit_bitcoin"
        const val TRNSACTION_TYPE_BRL_WALLET_CREDIT = "credit_brl"

        const val TRNSACTION_TYPE_BRITA_WALLET_DEBIT = "debit_brita"
        const val TRNSACTION_TYPE_BITCOIN_WALLET_DEBIT = "debit_bitcoin"
        const val TRNSACTION_TYPE_BRL_WALLET_DEBIT = "debit_brl"
    }
}