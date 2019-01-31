package com.mofaia.mypay.app.feature.createAccount

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.constant.INITIAL_CREDIT_VALUE_IN_WALLET
import com.mofaia.mypay.app.data.entity.User
import com.mofaia.mypay.app.data.repository.authentication.AuthenticationDataSource
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource
import java.math.BigDecimal

class CreateAccountViewModel(private val authenticationRepository: AuthenticationDataSource
                             , private val  walletRepository: WalletDataSource)
    : ViewModel() {

    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val repeatPassword = ObservableField<String>()
    val isValid = ObservableBoolean(false)
    val accountCreatedSuccessfully = MutableLiveData<Boolean>()
    val waitingForAccountCreation = ObservableBoolean(false)

    fun createAccount() {
        stateChangeToAttemptingCreateAccount()
        validate({ email: String, password: String ->
            authenticationRepository.createAccount(email, password, {
                stateChangeToCreateAccountSuccessfully()
                creditInitialValueInWallet(it)
            } , this::stateChangeToCreateAccountFailure)
        })
    }

    fun applyValidation() {
        validate({ _: String, _: String -> isValid.set(true) }, {isValid.set(false)})
    }

    private fun creditInitialValueInWallet(user: User) {
        walletRepository.creditBRL(BigDecimal(INITIAL_CREDIT_VALUE_IN_WALLET), user.id)
    }

    private fun stateChangeToAttemptingCreateAccount() {
        waitingForAccountCreation.set(true)
    }

    private fun stateChangeToCreateAccountSuccessfully() {
        waitingForAccountCreation.set(false)
        accountCreatedSuccessfully.value = true
    }

    private fun stateChangeToCreateAccountFailure() {
        waitingForAccountCreation.set(false)
        accountCreatedSuccessfully.value = false
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