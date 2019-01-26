package com.mofaia.mypay.app.data.repository.wallet

class WalletRepository(private val dataSource: WalletDataSource): WalletDataSource {
    override fun creditBRL(value: Double, userId: String) {
        dataSource.creditBRL(value, userId)
    }

    override fun creditBRL(value: Double) {
        dataSource.creditBRL(value)
    }

    override fun creditBitcoin(value: Double) {
        dataSource.creditBitcoin(value)
    }

    override fun creditBrita(value: Double) {
        dataSource.creditBrita(value)
    }

    override fun debitBRL(value: Double) {
        dataSource.debitBRL(value)
    }

    override fun debitBitcoin(value: Double) {
        dataSource.debitBitcoin(value)
    }

    override fun debitBrita(value: Double) {
        dataSource.debitBrita(value)
    }

    override fun getExtract() {
    }

    override fun getBRLBalance(onSuccess: (Double) -> Unit, onError: () -> Unit) {
        dataSource.getBRLBalance(onSuccess, onError)
    }

    override fun getBritaBalance(onSuccess: (Double) -> Unit, onError: () -> Unit) {
        dataSource.getBritaBalance(onSuccess, onError)
    }

    override fun getBiticoinBalance(onSuccess: (Double) -> Unit, onError: () -> Unit) {
        dataSource.getBiticoinBalance(onSuccess, onError)
    }

}
