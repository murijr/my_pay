package com.mofaia.mypay.app.feature.authentication

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource

class CreateAccountViewModel(private val authenticationRepository: AuthenticationDataSource)
    : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val repeatPassword = ObservableField<String>()
    val isValid = ObservableBoolean(false)

    fun createAccount() {
        performValidation({ email: String, password: String ->
            authenticationRepository.createAccount(email, password, {}, {})
        })
    }

    fun applyValidation() {
        performValidation({ _: String, _: String -> isValid.set(true) }, {isValid.set(false)})
    }

    private fun performValidation(onSuccess: ((String, String) -> Unit)? = null
                                  , onError: (() -> Unit)? = null) {
        validate(email.get(), password.get(), repeatPassword.get(),{ email, password ->
            onSuccess?.invoke(email, password)
        }, {
            onError?.invoke()
        })
    }

    private fun validate(email: String?, password: String?, repeatPassword: String?
                        , onSuccess: (String, String) -> Unit, onError: () -> Unit) {

        if((!email.isNullOrBlank()) && (!password.isNullOrBlank() && password == repeatPassword)) {
            onSuccess(email, password)
        }else {
            onError()
        }

    }

}