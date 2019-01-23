package com.mofaia.mypay.app

import android.app.Application
import com.mofaia.mypay.app.di.repositoryModule
import com.mofaia.mypay.app.feature.createAccount.authenticationModule
import org.koin.android.ext.android.startKoin

class MyPayApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, arrayListOf(repositoryModule, authenticationModule))
    }

}