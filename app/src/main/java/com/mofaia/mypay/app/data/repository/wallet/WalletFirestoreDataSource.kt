package com.mofaia.mypay.app.data.repository.wallet

import com.google.firebase.firestore.CollectionReference
import com.mofaia.mypay.app.constant.COLLECTION_TRANSACTIONS
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.data.mapper.TransactionMapper
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource
import java.math.BigDecimal

class WalletFirestoreDataSource(private val collections: CollectionReference
                                , private val authenticationRepository: AuthenticationDataSource
                                , private val transactionMapper: TransactionMapper)
    : WalletDataSource {
    private fun generateTransaction(value: BigDecimal, transactionType: String) = Transaction(value
            , transactionType)

    private fun getUserId() = authenticationRepository.getCurrentUser()!!.id

    private fun add(transaction: Transaction) {
        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).add(transactionMapper fromObject transaction)
    }

    private fun add(transaction: Transaction, userId: String) {
        collections.document(userId).collection(COLLECTION_TRANSACTIONS).add(transactionMapper fromObject transaction)
    }

    override fun creditBRL(value: BigDecimal) {
        add(generateTransaction(value
                , Transaction.TRANSACTION_TYPE_BRL_WALLET_CREDIT))
    }

    override fun creditBRL(value: BigDecimal, userId: String) {
        add(generateTransaction(value
                , Transaction.TRANSACTION_TYPE_BRL_WALLET_CREDIT), userId)
    }

    override fun creditBitcoin(value: BigDecimal) {
        add(generateTransaction(value
                , Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_CREDIT))
    }

    override fun creditBrita(value: BigDecimal) {
        add(generateTransaction(value
                , Transaction.TRANSACTION_TYPE_BRITA_WALLET_CREDIT))
    }

    override fun debitBRL(value: BigDecimal) {
        add(generateTransaction(value
                , Transaction.TRANSACTION_TYPE_BRL_WALLET_DEBIT))
    }

    override fun debitBitcoin(value: BigDecimal) {
        add(generateTransaction(value
                , Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_DEBIT))
    }

    override fun debitBrita(value: BigDecimal) {
        add(generateTransaction(value
                , Transaction.TRANSACTION_TYPE_BRITA_WALLET_DEBIT))
    }


    private fun calculateBalance(transactions: List<Transaction>, creditTransactionType: String
                      , debitTransactionType: String): BigDecimal {

        val creditTransactions = transactions
                .filter { it.type.equals(creditTransactionType) }
                .flatMap { listOf(it.value) }
                .fold(BigDecimal.ZERO) { sum, element ->
                    sum.plus(element!!)
                }

        val debitTransactions = transactions
                .filter { it.type.equals(debitTransactionType) }
                .flatMap { listOf(it.value) }
                .fold(BigDecimal.ZERO) { sum, element ->
                    sum.plus(element!!)
                }

        return creditTransactions.minus(debitTransactions)

    }

    override fun getBRLBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {

        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {

                val transactionList = transactionMapper toObject snapshot!!.documents.toList()
                val calculateBalance = calculateBalance(transactionList, Transaction.TRANSACTION_TYPE_BRL_WALLET_CREDIT,
                        Transaction.TRANSACTION_TYPE_BRL_WALLET_DEBIT)
                onSuccess(calculateBalance)
            }

        }

    }

    override fun getBritaBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {
        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {
                val transactionList = transactionMapper toObject snapshot!!.documents.toList()
                val calculateBalance = calculateBalance(transactionList, Transaction.TRANSACTION_TYPE_BRITA_WALLET_CREDIT,
                        Transaction.TRANSACTION_TYPE_BRITA_WALLET_DEBIT)
                onSuccess(calculateBalance)
            }

        }

    }

    override fun getBiticoinBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {
        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {
                val transactionList = transactionMapper toObject snapshot!!.documents.toList()
                val calculateBalance = calculateBalance(transactionList, Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_CREDIT,
                        Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_DEBIT)
                onSuccess(calculateBalance)
            }

        }
    }

    override fun getExtract() {

    }

}
