package com.mofaia.mypay.app.data.repository.wallet

import com.mofaia.mypay.app.data.entity.Transaction
import java.math.BigDecimal

interface WalletDataSource {

    fun credit(value: BigDecimal, transactionType: Transaction.Type)

    fun credit(value: BigDecimal, userId: String, transactionType: Transaction.Type)

    fun debit(value: BigDecimal, transactionType: Transaction.Type)

    fun getExtract(onSuccess: (List<Transaction>) -> Unit, onError: () -> Unit)

    fun getBalance(creditTransactionType: Transaction.Type, debitTransactionType: Transaction.Type
                   , onSuccess: (BigDecimal) -> Unit, onError: () -> Unit)

}