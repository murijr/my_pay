package com.mofaia.mypay.app.data.repository.wallet

import com.google.firebase.firestore.CollectionReference

class WalletFirestoreDataSource(private val collections: CollectionReference): WalletDataSource {

    override fun creditBRL(value: Double) {
    }

    override fun creditBitcoin(value: Double) {
    }

    override fun creditBrita(value: Double) {
    }

    override fun debitBRL(value: Double) {
    }

    override fun debitBitcoin(value: Double) {
    }

    override fun debitBrita(value: Double) {
    }

    override fun getExtract() {
    }

}
