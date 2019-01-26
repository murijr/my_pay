package com.mofaia.mypay.app.data.repository.wallet

interface WalletDataSource {

    fun creditBRL(value: Double)

    fun creditBitcoin(value: Double)

    fun creditBrita(value: Double)

    fun debitBRL(value: Double)

    fun debitBitcoin(value: Double)

    fun debitBrita(value: Double)

    fun getExtract()

}