package com.mofaia.mypay.app.feature.workspace

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.common.CurrencyConverter
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource
import com.mofaia.mypay.app.data.repository.quotation.QuotationDataSource
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource
import com.mofaia.mypay.app.extension.orEmpty
import java.math.BigDecimal

class WorkspaceViewModel(private val quotationRepository: QuotationDataSource
                         , private val walletRepository: WalletDataSource
                         , private val authenticationRepository: AuthenticationDataSource): ViewModel() {

    val balanceBRL = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val balanceBrita = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val balanceBitcoin = ObservableField<BigDecimal>(BigDecimal.ZERO)

    val purchaseQuotationBitcoin = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val purchaseQuotationBrita = ObservableField<BigDecimal>(BigDecimal.ZERO)

    val salesQuotationBitcoin = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val salesQuotationBrita = ObservableField<BigDecimal>(BigDecimal.ZERO)

    val exchangeQuotationBitcoin = ObservableField<BigDecimal>(BigDecimal("3411.36"))
    val exchangeQuotationBrita = ObservableField<BigDecimal>(BigDecimal("0.00029"))

    init {
        loadData()
    }

    private fun loadData() {
        quotationRepository.syncQuotations()
        observeBalance()
        observeQuotation()
    }

    private fun observeBalance() {
        walletRepository.getBalance(Transaction.Type.BRL_CREDIT, Transaction.Type.BRL_DEBIT, balanceBRL::set) {}
        walletRepository.getBalance(Transaction.Type.BITCOIN_CREDIT, Transaction.Type.BITCOIN_DEBIT, balanceBitcoin::set) {}
        walletRepository.getBalance(Transaction.Type.BRITA_CREDIT, Transaction.Type.BRITA_DEBIT, balanceBrita::set) {}
    }

    private fun observeQuotation() {

        quotationRepository.getBitcoinQuotation({
            purchaseQuotationBitcoin.set(it.purchaseQuotation.orEmpty())
            salesQuotationBitcoin.set(it.salesQuotation.orEmpty())
        }, {})

        quotationRepository.getBritaQuotation({
            purchaseQuotationBrita.set(it.purchaseQuotation.orEmpty())
            salesQuotationBrita.set(it.salesQuotation.orEmpty())
        }, {})

    }


    fun logout() {
        authenticationRepository.logout()
    }

}