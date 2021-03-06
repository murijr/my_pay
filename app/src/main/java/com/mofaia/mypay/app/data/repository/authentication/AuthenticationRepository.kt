package com.mofaia.mypay.app.data.repository.authentication

import com.mofaia.mypay.app.data.entity.User

class AuthenticationRepository(private val dataSource: AuthenticationDataSource):
        AuthenticationDataSource {

    override fun login(email: String, password: String, onSuccess: () -> Unit
                       , onError: () -> Unit) {
        dataSource.login(email, password, onSuccess, onError)
    }

    override fun logout() {
        dataSource.logout()
    }

    override fun createAccount(email: String, password: String, onSuccess: (User) -> Unit
                               , onError: () -> Unit) {
        dataSource.createAccount(email, password, onSuccess, onError)
    }

    override fun getCurrentUser() = dataSource.getCurrentUser()

}