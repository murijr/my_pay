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
    val transactionType = ObservableField<Transaction.Type>()
    val quotation = ObservableField<BigDecimal>()
    val balance = ObservableField<BigDecimal>()
    val isValid = ObservableBoolean(false)
    val transactionWasPerformed = MutableLiveData<Boolean>()

    fun executeTransaction() {

        when(transactionType.get()) {
            Transaction.Type.BITCOIN_CREDIT -> purchaseBitcoin()
            Transaction.Type.BRITA_CREDIT -> purchaseBrita()
            Transaction.Type.BITCOIN_DEBIT -> saleBitcoin()
            Transaction.Type.BRITA_DEBIT -> saleBrita()
            Transaction.Type.BITCOIN_EXCHANGE -> exchangeBitcoin()
            Transaction.Type.BRITA_EXCHANGE -> exchangeBrita()
            else -> {
            }
        }

    }

    private fun exchangeBrita() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debit(debit, Transaction.Type.BITCOIN_CREDIT)
        walletRepository.credit(amount.get()!!, Transaction.Type.BRITA_DEBIT)
        transactionWasPerformed.value = true
    }

    private fun exchangeBitcoin() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debit(debit, Transaction.Type.BRITA_CREDIT)
        walletRepository.credit(amount.get()!!, Transaction.Type.BITCOIN_DEBIT)
        transactionWasPerformed.value = true
    }

    private fun purchaseBrita() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debit(debit, Transaction.Type.BRL_DEBIT)
        walletRepository.credit(amount.get()!!, Transaction.Type.BRITA_CREDIT)
        transactionWasPerformed.value = true
    }

    private fun purchaseBitcoin() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debit(debit, Transaction.Type.BRL_DEBIT)
        walletRepository.credit(amount.get()!!, Transaction.Type.BITCOIN_CREDIT)
        transactionWasPerformed.value = true
    }

    private fun saleBrita() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debit(debit, Transaction.Type.BRITA_DEBIT)
        walletRepository.credit(amount.get()!!, Transaction.Type.BRL_CREDIT)
        transactionWasPerformed.value = true
    }

    private fun saleBitcoin() {
        val debit = currencyConverter.convert(amount.get()!!, quotation.get()!!)
        walletRepository.debit(debit, Transaction.Type.BITCOIN_DEBIT)
        walletRepository.credit(amount.get()!!, Transaction.Type.BRL_CREDIT)
        transactionWasPerformed.value = true
    }

    fun applyValidation() {
        validatePurchase()
        validateSale()
    }

    private fun validateSale() {
        if((transactionType.get() != Transaction.Type.BITCOIN_DEBIT && transactionType.get() != Transaction.Type.BRITA_DEBIT)
                && (transactionType.get() != Transaction.Type.BRITA_EXCHANGE && transactionType.get() != Transaction.Type.BITCOIN_EXCHANGE)
        ) return
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
        if(transactionType.get() != Transaction.Type.BITCOIN_CREDIT
                && transactionType.get() != Transaction.Type.BRITA_CREDIT) return
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