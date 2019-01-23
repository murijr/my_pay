package com.mofaia.mypay.app.data.repository.authentication

import com.mofaia.mypay.app.data.entity.User

interface AuthenticationDataSource {

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit)

    fun logout()

    fun createAccount(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit)

    fun getCurrentUser(): User?

}