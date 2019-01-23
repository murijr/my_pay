package com.mofaia.mypay.app.feature.createAccount

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.databinding.ActivityCreateAccountBinding
import com.mofaia.mypay.app.extension.configureToolbar
import com.mofaia.mypay.app.extension.setContentView
import com.mofaia.mypay.app.extension.showToast
import com.mofaia.mypay.app.feature.userAuth.UserAuthActivity
import org.koin.android.viewmodel.ext.android.viewModel

class CreateAccountActivity : AppCompatActivity() {

    private val  viewModel: CreateAccountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityCreateAccountBinding>(viewModel, R.layout.activity_create_account)
        handleUI()
        configureObservers()
    }

    private fun handleUI() {
        configureToolbar()
    }

    private fun configureObservers() {
        observeAttemptToCreateAccount()
    }

    private fun observeAttemptToCreateAccount() {
        viewModel.accountCreatedSuccessfully
                .observe(this, Observer {
                    if(it) {
                        handleAccountCreatedSuccessfully()
                    } else {
                        handleAccountCreatedFailure()
                    }
        })
    }

    private fun handleAccountCreatedSuccessfully() {
        showToast(R.string.text_account_created_successfully)
        UserAuthActivity.start(this)
        finishAfterTransition()
    }

    private fun handleAccountCreatedFailure() {
        showToast(R.string.text_account_created_failure)
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, CreateAccountActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
