package com.mofaia.mypay.app.data.repository.quotation

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BitcoinQuotationService {
    @GET("BTC/ticker/")
    fun getQuotations(): Deferred<Response<Map<String, Any>>>
}