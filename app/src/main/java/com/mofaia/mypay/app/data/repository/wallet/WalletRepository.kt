package com.mofaia.mypay.app.data.repository.wallet

import com.mofaia.mypay.app.data.entity.Transaction
import java.math.BigDecimal

class WalletRepository(private val dataSource: WalletDataSource): WalletDataSource {

    override fun credit(value: BigDecimal, transactionType: Transaction.Type) {
        dataSource.credit(value, transactionType)
    }

    override fun credit(value: BigDecimal, userId: String, transactionType: Transaction.Type) {
        dataSource.credit(value, userId, transactionType)
    }

    override fun debit(value: BigDecimal, transactionType: Transaction.Type) {
        dataSource.debit(value, transactionType)
    }

    override fun getExtract(onSuccess: (List<Transaction>) -> Unit, onError: () -> Unit) {
        dataSource.getExtract(onSuccess, onError)
    }

    override fun getBalance(creditTransactionType: Transaction.Type
                            , debitTransactionType: Transaction.Type
                            , onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {
        dataSource.getBalance(creditTransactionType, debitTransactionType, onSuccess, onError)
    }

}
