package com.mofaia.mypay.app.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mofaia.mypay.app.constant.RETROFIT_BUILDER_DEFAULT
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    factory<Retrofit.Builder>(RETROFIT_BUILDER_DEFAULT) {
        Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
    }

}