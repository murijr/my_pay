package com.mofaia.mypay.app.di

import com.google.firebase.firestore.FirebaseFirestore
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mofaia.mypay.app.constant.*
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationFirebaseDataSource
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationRepository
import com.mofaia.mypay.app.data.repository.quotation.*
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource
import com.mofaia.mypay.app.data.repository.wallet.WalletFirestoreDataSource
import com.mofaia.mypay.app.data.repository.wallet.WalletRepository
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoryModule = module {

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

    factory<AuthenticationDataSource> {AuthenticationRepository(AuthenticationFirebaseDataSource())}

    factory(FIRESTORE_COLLECTION_QUOTATIONS){FirebaseFirestore.getInstance().collection(COLLECTION_QUOTATION)}

    factory<QuotationDataSource>(QUOTATIONS_FIRESTORE_DATA_SOURCE){ QuotationFirestoreDataSource(get(FIRESTORE_COLLECTION_QUOTATIONS)) }

    factory(FIRESTORE_COLLECTION_WALLET){FirebaseFirestore.getInstance().collection(COLLECTION_WALLET)}

    factory<WalletDataSource>(WALLET_FIRESTORE_DATA_SOURCE){ WalletFirestoreDataSource(get(FIRESTORE_COLLECTION_WALLET)) }

    single<WalletDataSource>(WALLET_REPOSITORY){
        WalletRepository(get(WALLET_FIRESTORE_DATA_SOURCE)) }

    single<QuotationDataSource>(QUOTATIONS_REPOSITORY){
        QuotationRepository(get(QUOTATIONS_FIRESTORE_DATA_SOURCE), get(), get(), get(), get(), get()) }

}