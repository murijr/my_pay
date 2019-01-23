package com.mofaia.mypay.app.di

import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationFirebaseDataSource
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    factory<AuthenticationDataSource> {AuthenticationRepository(AuthenticationFirebaseDataSource())}
}