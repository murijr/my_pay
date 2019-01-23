package com.mofaia.mypay.app.feature.createAccount

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val createAccountModule = module {
    viewModel { CreateAccountViewModel(get()) }
}