package com.mofaia.mypay.app.di

import com.google.firebase.firestore.FirebaseFirestore
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

val repositoryModule = module {

    factory<AuthenticationDataSource> {
        AuthenticationRepository(AuthenticationFirebaseDataSource())
    }

    factory(FIRESTORE_COLLECTION_QUOTATIONS){
        FirebaseFirestore.getInstance().collection(COLLECTION_QUOTATION)
    }

    factory<QuotationDataSource>(QUOTATIONS_FIRESTORE_DATA_SOURCE){
        QuotationFirestoreDataSource(get(FIRESTORE_COLLECTION_QUOTATIONS), get(), get())
    }

    factory(FIRESTORE_COLLECTION_WALLET){
        FirebaseFirestore.getInstance().collection(COLLECTION_WALLET)
    }

    factory<WalletDataSource>(WALLET_FIRESTORE_DATA_SOURCE){
        WalletFirestoreDataSource(get(FIRESTORE_COLLECTION_WALLET), get(), get())
    }

    single<WalletDataSource>(WALLET_REPOSITORY){
        WalletRepository(get(WALLET_FIRESTORE_DATA_SOURCE)) }

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

    single<QuotationDataSource>(QUOTATIONS_REPOSITORY){
        QuotationRepository(get(QUOTATIONS_FIRESTORE_DATA_SOURCE), get(), get(), get(), get()
                , get())
    }

}