package com.mofaia.mypay.app.di

import com.mofaia.mypay.app.data.mapper.QuotationBitcoinMapper
import com.mofaia.mypay.app.data.mapper.QuotationBritaMapper
import org.koin.dsl.module.module

val mapperModule = module {
    factory { QuotationBitcoinMapper(get()) }
    factory { QuotationBritaMapper(get()) }
}