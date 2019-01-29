package com.mofaia.mypay.app.feature.workspace

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.common.CurrencyConverter
import com.mofaia.mypay.app.data.repository.quotation.QuotationDataSource
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource
import com.mofaia.mypay.app.extension.toMoney
import java.math.BigDecimal

class WorkspaceViewModel(quotationRepository: QuotationDataSource
                         , walletRepository: WalletDataSource
                         , private val currencyConverter: CurrencyConverter): ViewModel() {

    val balanceBRL = ObservableField<Double>(0.0)
    val balanceBrita = ObservableField<Double>(0.0)
    val balanceBitcoin = ObservableField<Double>(0.0)

    val purchaseQuotationBitcoin = ObservableField<Double>(0.0)
    val purchaseQuotationBrita = ObservableField<Double>(0.0)

    val salesQuotationBitcoin = ObservableField<Double>(0.0)
    val salesQuotationBrita = ObservableField<Double>(0.0)

    val exchangeQuotationBitcoin = ObservableField<Double>(0.0)
    val exchangeQuotationBrita = ObservableField<Double>(0.0)


    init {

        quotationRepository.syncQuotations()

        walletRepository.getBRLBalance(balanceBRL::set) {}
        walletRepository.getBiticoinBalance(balanceBitcoin::set) {}
        walletRepository.getBritaBalance(balanceBrita::set) {}

        quotationRepository.getBitcoinQuotation({
            purchaseQuotationBitcoin.set(it.purchaseQuotation)
            salesQuotationBitcoin.set(it.salesQuotation)
            applyExchangeQuotation()
        }, {})

        quotationRepository.getBritaQuotation({
            purchaseQuotationBrita.set(it.purchaseQuotation)
            salesQuotationBrita.set(it.salesQuotation)
            applyExchangeQuotation()
        }, {})

    }

    private fun applyExchangeQuotation() {
        exchangeQuotationBitcoin.set(getExchangeQuotationBitcoin())
        exchangeQuotationBrita.set(getExchangeQuotationBrita())
    }

    private fun getExchangeQuotationBrita(): Double {
        if(salesQuotationBitcoin.get()?.toMoney()?.amount!! <= BigDecimal.ZERO
                || salesQuotationBrita.get()?.toMoney()?.amount!! <= BigDecimal.ZERO) {
            return 0.0
        }
        return currencyConverter.getQuotation(salesQuotationBrita.get()?.toMoney()?.amount?.toDouble()!!
                , salesQuotationBitcoin.get()?.toMoney()?.amount?.toDouble()!!)
    }

    private fun getExchangeQuotationBitcoin(): Double {
        if(salesQuotationBitcoin.get()?.toMoney()?.amount!! <= BigDecimal.ZERO
                || salesQuotationBrita.get()?.toMoney()?.amount!! <= BigDecimal.ZERO) {
            return 0.0
        }
        return currencyConverter.getQuotation(salesQuotationBitcoin.get()?.toMoney()?.amount?.toDouble()!!
                , salesQuotationBrita.get()?.toMoney()?.amount?.toDouble()!!)
    }

}