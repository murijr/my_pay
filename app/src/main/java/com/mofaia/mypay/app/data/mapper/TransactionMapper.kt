package com.mofaia.mypay.app.data.mapper

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.mofaia.mypay.app.data.entity.Transaction
import java.math.BigDecimal

class TransactionMapper{

    infix fun toObject(obj: List<DocumentSnapshot>): List<Transaction> {

        val transactionList = mutableListOf<Transaction>()

        obj.forEach { it ->

            val transaction = Transaction()

            it[Transaction.FIELD_VALUE]?.let {
                transaction.value = BigDecimal(it.toString())
            }
            it[Transaction.FIELD_TYPE]?.let {
                transaction.type = it.toString()
            }
            it[Transaction.FIELD_CREATE_AT]?.let {
                transaction.createAt = it as Timestamp
            }
            transactionList.add(transaction)
        }

        return transactionList

    }

    infix fun fromObject(obj: Transaction): Map<String, Any> {

        val map = mutableMapOf<String, Any>()
        map[Transaction.FIELD_VALUE] = obj.value.toString()
        map[Transaction.FIELD_TYPE] = obj.type.toString()
        map[Transaction.FIELD_CREATE_AT] = obj.createAt
        return  map.toMap()

    }

}