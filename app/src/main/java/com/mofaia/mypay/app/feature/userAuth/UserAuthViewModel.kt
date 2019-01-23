package com.mofaia.mypay.app.feature.userAuth

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource

class UserAuthViewModel(private val authenticationRepository: AuthenticationDataSource): ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val isValid = ObservableBoolean(false)
    val authSuccessfully = MutableLiveData<Boolean>()
    val waitingForUserAuth = ObservableBoolean(false)

    fun userAuth() {
        stateChangeToAttemptingUserAuth()
        validate({ email, password ->
            authenticationRepository.login(email, password, this::stateChangeToUserAuthSuccessfully
                    , this::stateChangeToUserAuthFailure)
        })
    }

    private fun stateChangeToAttemptingUserAuth() {
        waitingForUserAuth.set(true)
    }

    private fun stateChangeToUserAuthSuccessfully() {
        waitingForUserAuth.set(false)
        authSuccessfully.value = true
    }

    private fun stateChangeToUserAuthFailure() {
        waitingForUserAuth.set(false)
        authSuccessfully.value = false
    }

    fun applyValidation() {
        validate({ _: String, _: String -> isValid.set(true) }, {isValid.set(false)})
    }

    private fun validate(onSuccess: (String, String) -> Unit, onError: (() -> Unit)? = null) {

        val emailProvided = email.get().orEmpty()
        val passwordProvided = password.get().orEmpty()

        if(!emailProvided.isBlank() && !passwordProvided.isBlank()) {
            onSuccess(emailProvided, passwordProvided)
        }else {
            onError?.invoke()
        }

    }


}