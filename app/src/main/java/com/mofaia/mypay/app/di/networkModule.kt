package com.mofaia.mypay.app.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mofaia.mypay.app.constant.QUOTATION_BITCOIN_ENDPOINT
import com.mofaia.mypay.app.constant.QUOTATION_BRITA_ENDPOINT
import com.mofaia.mypay.app.constant.RETROFIT_BUILDER_DEFAULT
import com.mofaia.mypay.app.data.repository.quotation.BitcoinQuotationService
import com.mofaia.mypay.app.data.repository.quotation.BritaQuotationService
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory<Retrofit.Builder>(RETROFIT_BUILDER_DEFAULT) {
        Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
    }

    single<BritaQuotationService> {
        get<Retrofit.Builder>(RETROFIT_BUILDER_DEFAULT)
                .baseUrl(QUOTATION_BRITA_ENDPOINT)
                .build()
                .create(BritaQuotationService::class.java)
    }

    single<BitcoinQuotationService> {
        get<Retrofit.Builder>(RETROFIT_BUILDER_DEFAULT)
                .baseUrl(QUOTATION_BITCOIN_ENDPOINT)
                .build()
                .create(BitcoinQuotationService::class.java)
    }

}