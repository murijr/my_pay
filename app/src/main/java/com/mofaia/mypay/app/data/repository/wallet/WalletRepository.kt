package com.mofaia.mypay.app.data.repository.wallet

import java.math.BigDecimal

class WalletRepository(private val dataSource: WalletDataSource): WalletDataSource {
    override fun creditBRL(value: BigDecimal, userId: String) {
        dataSource.creditBRL(value, userId)
    }

    override fun creditBRL(value: BigDecimal) {
        dataSource.creditBRL(value)
    }

    override fun creditBitcoin(value: BigDecimal) {
        dataSource.creditBitcoin(value)
    }

    override fun creditBrita(value: BigDecimal) {
        dataSource.creditBrita(value)
    }

    override fun debitBRL(value: BigDecimal) {
        dataSource.debitBRL(value)
    }

    override fun debitBitcoin(value: BigDecimal) {
        dataSource.debitBitcoin(value)
    }

    override fun debitBrita(value: BigDecimal) {
        dataSource.debitBrita(value)
    }

    override fun getExtract() {
    }

    override fun getBRLBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {
        dataSource.getBRLBalance(onSuccess, onError)
    }

    override fun getBritaBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {
        dataSource.getBritaBalance(onSuccess, onError)
    }

    override fun getBiticoinBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit) {
        dataSource.getBiticoinBalance(onSuccess, onError)
    }

}
