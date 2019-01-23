package com.mofaia.mypay.app.feature.createAccount

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource

class CreateAccountViewModel(private val authenticationRepository: AuthenticationDataSource)
    : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val repeatPassword = ObservableField<String>()
    val isValid = ObservableBoolean(false)
    val accountCreatedSuccessfully = MutableLiveData<Boolean>()
    val waiting = ObservableBoolean(false)

    fun createAccount() {
        waiting.set(true)
        validate({ email: String, password: String ->
            authenticationRepository.createAccount(email, password, {
                waiting.set(false)
                accountCreatedSuccessfully.value = true
            }, {
                waiting.set(false)
                accountCreatedSuccessfully.value = false
            })
        })
    }

    fun applyValidation() {
        validate({ _: String, _: String -> isValid.set(true) }, {isValid.set(false)})
    }

    private fun validate(onSuccess: (String, String) -> Unit, onError: (() -> Unit)? = null) {

        val emailProvided = email.get().orEmpty()
        val passwordProvided = password.get().orEmpty()
        val repeatedPasswordProvided = repeatPassword.get().orEmpty()

        if((!emailProvided.isBlank()) && (!passwordProvided.isBlank()
                        && passwordProvided == repeatedPasswordProvided)) {
            onSuccess(emailProvided, passwordProvided)
        }else {
            onError?.invoke()
        }

    }

}