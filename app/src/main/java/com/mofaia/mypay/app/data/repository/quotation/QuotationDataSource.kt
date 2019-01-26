package com.mofaia.mypay.app.data.repository.quotation

import com.mofaia.mypay.app.data.entity.Quotation

interface QuotationDataSource {

    fun syncQuotations()

    fun saveBritaQuotation(quotation: Quotation)

    fun saveBitcoinQuotation(quotation: Quotation)

    fun getBritaQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit)

    fun getBitcoinQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit)

}