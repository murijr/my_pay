package com.mofaia.mypay.app.data.repository.quotation

import com.mofaia.mypay.app.common.DateManipulator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuotationRepository(private val serviceBrita: BritaQuotationService
                          , private val dataManipulator: DateManipulator): QuotationDataSource {

    override fun syncQuotations() {

        GlobalScope.launch {

            val response = serviceBrita
                    .getQuotations(dataManipulator.getCurrentDate()).await()

            if(response.isSuccessful) {
                TODO()
            }else {
                TODO()
            }

        }


    }

    override fun getAllQuotations() {

    }

    override fun saveQuotation() {

    }

}
