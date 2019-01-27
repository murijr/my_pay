package com.mofaia.mypay.app.data.repository.wallet

import com.google.firebase.firestore.CollectionReference
import com.mofaia.mypay.app.constant.COLLECTION_TRANSACTIONS
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource
import com.mofaia.mypay.app.extension.toMoney

class WalletFirestoreDataSource(private val collections: CollectionReference
                                , private val authenticationRepository: AuthenticationDataSource)
    : WalletDataSource {
    private fun generateTransaction(value: Double, transactionType: String) = Transaction(value.toMoney().amount.toDouble()
            , transactionType)

    private fun getUserId() = authenticationRepository.getCurrentUser()!!.id

    private fun add(transaction: Transaction) {
        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).add(transaction)
    }

    private fun add(transaction: Transaction, userId: String) {
        collections.document(userId).collection(COLLECTION_TRANSACTIONS).add(transaction)
    }

    override fun creditBRL(value: Double) {
        add(generateTransaction(value
                , Transaction.TRNSACTION_TYPE_BRL_WALLET_CREDIT))
    }

    override fun creditBRL(value: Double, userId: String) {
        add(generateTransaction(value
                , Transaction.TRNSACTION_TYPE_BRL_WALLET_CREDIT), userId)
    }

    override fun creditBitcoin(value: Double) {
        add(generateTransaction(value
                , Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_CREDIT))
    }

    override fun creditBrita(value: Double) {
        add(generateTransaction(value
                , Transaction.TRNSACTION_TYPE_BRITA_WALLET_CREDIT))
    }

    override fun debitBRL(value: Double) {
        add(generateTransaction(value
                , Transaction.TRNSACTION_TYPE_BRL_WALLET_DEBIT))
    }

    override fun debitBitcoin(value: Double) {
        add(generateTransaction(value
                , Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_DEBIT))
    }

    override fun debitBrita(value: Double) {
        add(generateTransaction(value
                , Transaction.TRNSACTION_TYPE_BRITA_WALLET_DEBIT))
    }


    private fun calculateBalance(transactions: List<Transaction>, creditTransactionType: String
                      , debitTransactionType: String): Double {

        val creditTransactions = transactions
                .filter { it.type.equals(creditTransactionType) }
                .flatMap { listOf(it.value) }
                .fold(0.0) { sum, element ->
                    sum.plus(element!!)
                }

        val debitTransactions = transactions
                .filter { it.type.equals(debitTransactionType) }
                .flatMap { listOf(it.value) }
                .fold(0.0) { sum, element ->
                    sum.plus(element!!)
                }

        return creditTransactions.minus(debitTransactions)

    }

    override fun getBRLBalance(onSuccess: (Double) -> Unit, onError: () -> Unit) {

        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {
                val transactionList = snapshot!!.toObjects(Transaction::class.java)
                val calculateBalance = calculateBalance(transactionList, Transaction.TRNSACTION_TYPE_BRL_WALLET_CREDIT,
                        Transaction.TRNSACTION_TYPE_BRL_WALLET_DEBIT)
                onSuccess(calculateBalance)
            }

        }

    }

    override fun getBritaBalance(onSuccess: (Double) -> Unit, onError: () -> Unit) {
        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {
                val transactionList = snapshot!!.toObjects(Transaction::class.java)
                val calculateBalance = calculateBalance(transactionList, Transaction.TRNSACTION_TYPE_BRITA_WALLET_CREDIT,
                        Transaction.TRNSACTION_TYPE_BRITA_WALLET_DEBIT)
                onSuccess(calculateBalance)
            }

        }

    }

    override fun getBiticoinBalance(onSuccess: (Double) -> Unit, onError: () -> Unit) {
        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {
                val transactionList = snapshot!!.toObjects(Transaction::class.java)
                val calculateBalance = calculateBalance(transactionList, Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_CREDIT,
                        Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_DEBIT)
                onSuccess(calculateBalance)
            }

        }
    }

    override fun getExtract() {

    }

}
