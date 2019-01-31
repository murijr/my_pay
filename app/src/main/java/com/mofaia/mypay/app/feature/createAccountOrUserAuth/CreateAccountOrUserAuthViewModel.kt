package com.mofaia.mypay.app.feature.createAccountOrUserAuth

import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.data.entity.User
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource

class CreateAccountOrUserAuthViewModel
(authenticationRepository: AuthenticationDataSource): ViewModel() {

    val user: User? = authenticationRepository.getCurrentUser()

}