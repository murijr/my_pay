package com.mofaia.mypay.app

import android.app.Application
import com.mofaia.mypay.app.di.commonModule
import com.mofaia.mypay.app.di.mapperModule
import com.mofaia.mypay.app.di.repositoryModule
import com.mofaia.mypay.app.feature.createAccount.createAccountModule
import com.mofaia.mypay.app.feature.createAccountOrUserAuth.createAccountOrUserAuthModule
import com.mofaia.mypay.app.feature.userAuth.userAuthModule
import com.mofaia.mypay.app.feature.workspace.workspaceModule
import org.koin.android.ext.android.startKoin

class MyPayApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, arrayListOf(repositoryModule, createAccountModule
                , userAuthModule, createAccountOrUserAuthModule, workspaceModule
                , commonModule, mapperModule))
    }

}