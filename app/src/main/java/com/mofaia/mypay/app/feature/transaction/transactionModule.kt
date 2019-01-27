package com.mofaia.mypay.app.feature.transaction

import com.mofaia.mypay.app.common.CurrencyConverter
import com.mofaia.mypay.app.constant.WALLET_REPOSITORY
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val transactionModule = module {
    viewModel { TransactionViewModel(get(WALLET_REPOSITORY), get()) }
}