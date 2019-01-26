package com.mofaia.mypay.app.feature.createAccount

import com.mofaia.mypay.app.constant.WALLET_REPOSITORY
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val createAccountModule = module {
    viewModel { CreateAccountViewModel(get(), get(WALLET_REPOSITORY)) }
}