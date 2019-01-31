package com.mofaia.mypay.app.data.repository.wallet

import java.math.BigDecimal

interface WalletDataSource {

    fun creditBRL(value: BigDecimal)

    fun creditBRL(value: BigDecimal, userId: String)

    fun creditBitcoin(value: BigDecimal)

    fun creditBrita(value: BigDecimal)

    fun debitBRL(value: BigDecimal)

    fun debitBitcoin(value: BigDecimal)

    fun debitBrita(value: BigDecimal)

    fun getExtract()

    fun getBRLBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit)

    fun getBritaBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit)

    fun getBiticoinBalance(onSuccess: (BigDecimal) -> Unit, onError: () -> Unit)

}