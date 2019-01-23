package com.mofaia.mypay.app.feature.userAuth

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val userAuthModule = module {
    viewModel { UserAuthViewModel(get()) }
}