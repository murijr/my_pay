package com.mofaia.mypay.app.feature.workspace

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.common.CurrencyConverter
import com.mofaia.mypay.app.data.repository.quotation.QuotationDataSource
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource
import com.mofaia.mypay.app.extension.orEmpty
import java.math.BigDecimal

class WorkspaceViewModel(quotationRepository: QuotationDataSource
                         , walletRepository: WalletDataSource
                         , private val currencyConverter: CurrencyConverter): ViewModel() {

    val balanceBRL = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val balanceBrita = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val balanceBitcoin = ObservableField<BigDecimal>(BigDecimal.ZERO)

    val purchaseQuotationBitcoin = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val purchaseQuotationBrita = ObservableField<BigDecimal>(BigDecimal.ZERO)

    val salesQuotationBitcoin = ObservableField<BigDecimal>(BigDecimal.ZERO)
    val salesQuotationBrita = ObservableField<BigDecimal>(BigDecimal.ZERO)

    init {

        quotationRepository.syncQuotations()

        walletRepository.getBRLBalance(balanceBRL::set) {}
        walletRepository.getBiticoinBalance(balanceBitcoin::set) {}
        walletRepository.getBritaBalance(balanceBrita::set) {}

        quotationRepository.getBitcoinQuotation({
            purchaseQuotationBitcoin.set(it.purchaseQuotation.orEmpty())
            salesQuotationBitcoin.set(it.salesQuotation.orEmpty())
        }, {})

        quotationRepository.getBritaQuotation({
            purchaseQuotationBrita.set(it.purchaseQuotation.orEmpty())
            salesQuotationBrita.set(it.salesQuotation.orEmpty())
        }, {})

    }

}