package com.mofaia.mypay.app.feature.transaction

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableDouble
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.common.CurrencyConverter
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource
import com.mofaia.mypay.app.extension.toMoney
import java.math.BigDecimal

class TransactionViewModel(private val walletRepository: WalletDataSource
                           , private val currencyConverter: CurrencyConverter): ViewModel() {

    val amount = ObservableDouble()
    val transactionType = ObservableField<String>()
    val quotation = ObservableDouble()
    val quotationB = ObservableDouble()
    val balance = ObservableDouble()
    val isValid = ObservableBoolean(false)
    val transactionWasPerformed = MutableLiveData<Boolean>()

    fun executeTransaction() {

        when(transactionType.get()) {
            Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_CREDIT -> purchaseBitcoin()
            Transaction.TRANSACTION_TYPE_BRITA_WALLET_CREDIT -> purchaseBrita()
            Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_DEBIT -> saleBitcoin()
            Transaction.TRANSACTION_TYPE_BRITA_WALLET_DEBIT -> saleBrita()
            Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_EXCHANGE -> exchangeBitcoin()
            Transaction.TRANSACTION_TYPE_BRITA_WALLET_EXCHANGE -> exchangeBrita()
        }

    }

    private fun exchangeBrita() {
        val am = amount.get()
        val quo = quotation.get()

        val debit = currencyConverter.convert(amount.get(), quotation.get())
        //walletRepository.debitBrita(debit)
        //walletRepository.creditBitcoin(amount.get())
        transactionWasPerformed.value = true
    }

    private fun exchangeBitcoin() {

        val am = amount.get()
        val quo = quotation.get()

        val debit = currencyConverter.convert(amount.get(), quotation.get())
        //walletRepository.debitBitcoin(debit)
        //walletRepository.creditBrita(amount.get())
        transactionWasPerformed.value = true
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
        val debit = currencyConverter.convert(amount.get(), quotation.get())
        walletRepository.debitBrita(amount.get())
        walletRepository.creditBRL(debit)
        transactionWasPerformed.value = true
    }

    private fun saleBitcoin() {
        val debit = currencyConverter.convert(amount.get(), quotation.get())
        walletRepository.debitBitcoin(amount.get())
        walletRepository.creditBRL(debit)
        transactionWasPerformed.value = true
    }

    fun applyValidation() {
        validatePurchase()
        validateSale()
        validateExchange()
    }

    private fun validateExchange() {
        if(transactionType.get() != Transaction.TRANSACTION_TYPE_BITCOIN_WALLET_EXCHANGE
                && transactionType.get() != Transaction.TRANSACTION_TYPE_BRITA_WALLET_EXCHANGE) return
        isValid.set(true)
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
        return balance.toMoney().amount >= BigDecimal.ZERO
                && (amount.get().toMoney().amount <= balance.toMoney().amount)
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
        val debit = currencyConverter.convert(amount.get(), quotation.get())
        val balance = balance.get()
        return balance.toMoney().amount >= BigDecimal.ZERO && (amount.get().toMoney().amount
                <= balance.toMoney().amount) && debit.toMoney().amount <= balance.toMoney().amount
    }


}