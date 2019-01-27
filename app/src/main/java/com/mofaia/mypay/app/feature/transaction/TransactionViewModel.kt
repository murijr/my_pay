package com.mofaia.mypay.app.feature.transaction

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.common.CurrencyConverter
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource

class TransactionViewModel(private val walletRepository: WalletDataSource
                           , private val currencyConverter: CurrencyConverter): ViewModel() {

    val amount = ObservableDouble()
    val transactionType = ObservableField<String>()
    val quotation = ObservableDouble()
    val balance = ObservableDouble()
    val isValid = ObservableBoolean(false)
    val transactionWasPerformed = MutableLiveData<Boolean>()

    fun executeTransaction() {

        when(transactionType.get()) {
            Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_CREDIT -> purchaseBitcoin()
            Transaction.TRNSACTION_TYPE_BRITA_WALLET_CREDIT -> purchaseBrita()
        }

    }

    private fun purchaseBrita() {
        val debit = currencyConverter.convert(amount.get(), quotation.get())
        walletRepository.debitBRL(debit)
        walletRepository.creditBrita(amount.get())
        transactionWasPerformed.value = true
    }

    private fun purchaseBitcoin() {
        val debit = currencyConverter.convert(amount.get(), quotation.get())
        walletRepository.debitBRL(debit)
        walletRepository.creditBitcoin(amount.get())
        transactionWasPerformed.value = true
    }

    private fun saleBrita() {
        transactionWasPerformed.value = true
    }

    private fun saleBitcoin() {
        transactionWasPerformed.value = true
    }

    fun applyValidation() {
        validatePurchase()
    }

    private fun validatePurchase() {
        if(transactionType.get() != Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_CREDIT
                && transactionType.get() != Transaction.TRNSACTION_TYPE_BRITA_WALLET_CREDIT) return
        if(isValidPurchase()) {
            isValid.set(true)
        } else {
            isValid.set(false)
        }
    }

    private fun isValidPurchase(): Boolean {
        val debit = currencyConverter.convert(amount.get(), quotation.get())
        val balance = balance.get()
        return balance > 0 && (amount.get() <= balance) && debit <= balance
    }


}