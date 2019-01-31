package com.mofaia.mypay.app.feature.transactionList

import android.view.LayoutInflater
import com.mofaia.mypay.app.constant.WALLET_REPOSITORY
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val transactionListModule = module {
    viewModel { TransactionListViewModel(get(WALLET_REPOSITORY)) }
    factory { TransactionListAdapter( LayoutInflater.from(androidContext()), get()) }
}