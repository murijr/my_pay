package com.mofaia.mypay.app.data.repository.quotation

interface QuotationDataSource {

    fun syncQuotations()

    fun getAllQuotations()

    fun saveQuotation()

}