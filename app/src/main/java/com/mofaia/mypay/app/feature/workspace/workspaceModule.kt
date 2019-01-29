package com.mofaia.mypay.app.feature.workspace

import com.mofaia.mypay.app.constant.QUOTATIONS_REPOSITORY
import com.mofaia.mypay.app.constant.WALLET_REPOSITORY
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val workspaceModule = module {

    viewModel { WorkspaceViewModel(get(QUOTATIONS_REPOSITORY), get(WALLET_REPOSITORY), get()) }

}