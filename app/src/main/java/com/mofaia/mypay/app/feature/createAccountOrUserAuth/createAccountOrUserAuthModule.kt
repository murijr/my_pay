package com.mofaia.mypay.app.feature.createAccountOrUserAuth

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val createAccountOrUserAuthModule = module {
    viewModel { CreateAccountOrUserAuthViewModel(get()) }
}