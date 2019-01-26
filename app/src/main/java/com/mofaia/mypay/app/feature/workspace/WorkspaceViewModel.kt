package com.mofaia.mypay.app.feature.workspace

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.data.repository.quotation.QuotationDataSource

class WorkspaceViewModel(quotationRepository: QuotationDataSource): ViewModel() {

    val purchaseQuotationBitcoin = ObservableField<Double>()
    val purchaseQuotationBrita = ObservableField<Double>()

    val salesQuotationBitcoin = ObservableField<Double>()
    val salesQuotationBrita = ObservableField<Double>()


    init {

        quotationRepository.syncQuotations()

        quotationRepository.getBitcoinQuotation({

            purchaseQuotationBitcoin.set(it.purchaseQuotation)
            salesQuotationBitcoin.set(it.salesQuotation)

        }, {

        })

        quotationRepository.getBritaQuotation({

            purchaseQuotationBrita.set(it.purchaseQuotation)
            salesQuotationBrita.set(it.salesQuotation)

        }, {

        })

    }

}