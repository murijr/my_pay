package com.mofaia.mypay.core.data.repository.authentication

interface AuthenticationDataSource {

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit)

    fun logout()

    fun createAccount(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit)

    fun getCurrentUser()

}