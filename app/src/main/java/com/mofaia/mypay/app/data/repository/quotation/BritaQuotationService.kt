package com.mofaia.mypay.app.data.repository.quotation

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BritaQuotationService {
    @GET("odata/CotacaoDolarDia(dataCotacao='{date}')?format=json")
    fun getQuotations(@Path("date") date: String): Deferred<Response<Map<String, Any>>>
}