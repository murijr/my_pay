package com.mofaia.mypay.app.data.repository.quotation

import com.mofaia.mypay.app.common.DateManipulator
import com.mofaia.mypay.app.data.entity.Quotation
import com.mofaia.mypay.app.data.mapper.QuotationBitcoinMapper
import com.mofaia.mypay.app.data.mapper.QuotationBritaMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuotationRepository(private val storeDataSource: QuotationDataSource
                        , private val serviceBrita: BritaQuotationService
                        , private val serviceBitcoin: BitcoinQuotationService
                        , private val mapperBitcoinMapper: QuotationBitcoinMapper
                        , private val mapperBritaMapper: QuotationBritaMapper
                        , private val dataManipulator: DateManipulator): QuotationDataSource {

    override fun syncQuotations() {

        performSyncQuotations(onLoadBitcoinSuccess = this::saveBitcoinQuotation
                , onLoadBritaSuccess = this::saveBritaQuotation)

    }

    private fun performSyncQuotations(onLoadBitcoinSuccess: (Quotation) -> Unit
                                      , onLoadBritaSuccess: (Quotation) -> Unit) {

        GlobalScope.launch {

            val responseBrita = serviceBrita
                    .getQuotations(dataManipulator.getCurrentDate()).await()

            val responseBitcoin = serviceBitcoin.getQuotations().await()

            if(responseBrita.isSuccessful) {
                val quotation = mapperBritaMapper toObject responseBrita.body()
                quotation?.let {
                    onLoadBritaSuccess(it)
                }
            }

            if(responseBitcoin.isSuccessful) {
                val quotation = mapperBitcoinMapper toObject responseBitcoin.body()
                onLoadBitcoinSuccess(quotation)
            }

        }

    }

    override fun saveBritaQuotation(quotation: Quotation) {
        quotation.let {
            storeDataSource.saveBritaQuotation(it)
        }
    }

    override fun saveBitcoinQuotation(quotation: Quotation) {
        quotation.let {
            storeDataSource.saveBitcoinQuotation(it)
        }
    }

    override fun getBritaQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {
        storeDataSource.getBritaQuotation(onSuccess, onError)
    }

    override fun getBitcoinQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {
        storeDataSource.getBitcoinQuotation(onSuccess, onError)
    }

}
