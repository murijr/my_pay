package com.mofaia.mypay.app.data.repository.wallet

class WalletRepository(private val dataSource: WalletDataSource): WalletDataSource {

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