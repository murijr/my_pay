package com.mofaia.mypay.app.feature.transaction

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.common.CurrencyConverter
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource
import java.math.BigDecimal

class TransactionViewModel(private val walletRepository: WalletDataSource
                           , private val currencyConverter: CurrencyConverter): ViewModel() {

    val amount = ObservableField<BigDecimal>()
    val transactionType = ObservableField<String>()
    val quotation = ObservableField<BigDecimal>()
    val balance = ObservableField<BigDecimal>()
    val isValid = ObservableBoolean(false)
    val transactionWasPerformed = MutableLiveData<Boolean>()

    fun executeTransaction() {

        when(transactionType.get()) {
            Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_CREDIT -> purchaseBitcoin()
            Transaction.TRANSACTION_TYPE_BRITA_WALLET_CREDIT -> purchaseBrita()
            Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_DEBIT -> saleBitcoin()
            Transaction.TRANSACTION_TYPE_BRITA_WALLET_DEBIT -> saleBrita()
        }

    }

    private fun purchaseBrita() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debitBRL(debit)
        walletRepository.creditBrita(amount.get()!!)
        transactionWasPerformed.value = true
    }

    private fun purchaseBitcoin() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debitBRL(debit)
        walletRepository.creditBitcoin(amount.get()!!)
        transactionWasPerformed.value = true
    }

    private fun saleBrita() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debitBrita(amount.get()!!)
        walletRepository.creditBRL(debit)
        transactionWasPerformed.value = true
    }

    private fun saleBitcoin() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debitBitcoin(amount.get()!!)
        walletRepository.creditBRL(debit)
        transactionWasPerformed.value = true
    }

    fun applyValidation() {
        validatePurchase()
        validateSale()
    }

    private fun validateSale() {
        if(transactionType.get() != Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_DEBIT
                && transactionType.get() != Transaction.TRANSACTION_TYPE_BRITA_WALLET_DEBIT) return
        if(isValidSale()) {
            isValid.set(true)
        } else {
            isValid.set(false)
        }
    }

    private fun isValidSale(): Boolean {
        val balance = balance.get()
        return balance!! >= BigDecimal.ZERO
                && (amount.get()!! <= balance)
    }



    private fun validatePurchase() {
        if(transactionType.get() != Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_CREDIT
                && transactionType.get() != Transaction.TRANSACTION_TYPE_BRITA_WALLET_CREDIT) return
        if(isValidPurchase()) {
            isValid.set(true)
        } else {
            isValid.set(false)
        }
    }

    private fun isValidPurchase(): Boolean {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        val balance = balance.get()
        return balance!! >= BigDecimal.ZERO && (amount.get()!! <= balance) && debit <= balance
    }

}