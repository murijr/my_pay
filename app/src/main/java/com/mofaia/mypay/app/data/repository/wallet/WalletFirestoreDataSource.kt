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

    override fun credit(value: BigDecimal, transactionType: Transaction.Type) {
        add(generateTransaction(value, transactionType.toString()))
    }

    override fun credit(value: BigDecimal, userId: String, transactionType: Transaction.Type) {
        add(generateTransaction(value, transactionType.toString()), userId)
    }

    override fun debit(value: BigDecimal, transactionType: Transaction.Type) {
        add(generateTransaction(value, transactionType.toString()))
    }

    override fun getBalance(creditTransactionType: Transaction.Type, debitTransactionType: Transaction.Type
                            , onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {

        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {
                val transactionList = transactionMapper toObject snapshot!!.documents.toList()
                val calculateBalance = calculateBalance(transactionList
                        , creditTransactionType, debitTransactionType)
                onSuccess(calculateBalance)
            }
        }
    }

    override fun getExtract(onSuccess: (List<Transaction>) -> Unit, onError: () -> Unit) {

        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS).orderBy(Transaction.FIELD_CREATE_AT).addSnapshotListener {
            snapshot, exception ->
            if(exception != null && snapshot == null) {
                onError()
            } else {
                val transactionList = transactionMapper toObject snapshot!!.documents.toList()
                onSuccess(transactionList)
            }
        }
    }

    private fun generateTransaction(value: BigDecimal, transactionType: String) = Transaction(value
            , transactionType)

    private fun getUserId() = authenticationRepository.getCurrentUser()!!.id

    private fun add(transaction: Transaction) {
        collections.document(getUserId()).collection(COLLECTION_TRANSACTIONS)
                .add(transactionMapper fromObject transaction)
    }

    private fun add(transaction: Transaction, userId: String) {
        collections.document(userId).collection(COLLECTION_TRANSACTIONS)
                .add(transactionMapper fromObject transaction)
    }

    private fun calculateBalance(transactions: List<Transaction>, creditTransactionType: Transaction.Type
                                 , debitTransactionType: Transaction.Type): BigDecimal {

        val creditTransactions = transactions
                .filter { it.type.equals(creditTransactionType.toString()) }
                .flatMap { listOf(it.value) }
                .fold(BigDecimal.ZERO) { sum, element ->
                    sum.plus(element!!)
                }

        val debitTransactions = transactions
                .filter { it.type.equals(debitTransactionType.toString()) }
                .flatMap { listOf(it.value) }
                .fold(BigDecimal.ZERO) { sum, element ->
                    sum.plus(element!!)
                }

        return creditTransactions.minus(debitTransactions)

    }

}
