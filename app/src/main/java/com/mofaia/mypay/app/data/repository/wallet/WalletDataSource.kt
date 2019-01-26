package com.mofaia.mypay.app.data.repository.wallet

interface WalletDataSource {

    fun creditBRL(value: Double)

    fun creditBRL(value: Double, userId: String)

    fun creditBitcoin(value: Double)

    fun creditBrita(value: Double)

    fun debitBRL(value: Double)

    fun debitBitcoin(value: Double)

    fun debitBrita(value: Double)

    fun getExtract()

    fun getBRLBalance(onSuccess: (Double) -> Unit, onError: () -> Unit)

    fun getBritaBalance(onSuccess: (Double) -> Unit, onError: () -> Unit)

    fun getBiticoinBalance(onSuccess: (Double) -> Unit, onError: () -> Unit)

}