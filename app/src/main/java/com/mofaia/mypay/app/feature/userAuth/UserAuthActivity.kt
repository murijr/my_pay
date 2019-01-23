package com.mofaia.mypay.app.feature.userAuth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.databinding.ActivityUserAuthBinding
import com.mofaia.mypay.app.extension.configureToolbar
import com.mofaia.mypay.app.extension.setContentView
import com.mofaia.mypay.app.extension.showToast
import org.koin.android.viewmodel.ext.android.viewModel

class UserAuthActivity : AppCompatActivity() {

    val viewModel: UserAuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityUserAuthBinding>(viewModel, R.layout.activity_user_auth)
        handleUI()
        configureObservers()
    }

    private fun handleUI() {
        configureToolbar()
    }

    private fun configureObservers() {
        observeAuthAccount()
    }

    private fun observeAuthAccount() {
        viewModel.authSuccessfully
                .observe(this, Observer {
                    if(it) {
                        handleAuthSuccessfully()
                    } else {
                        handleAuthFailure()
                    }
                })
    }

    private fun handleAuthSuccessfully() {
        finishAfterTransition()
    }

    private fun handleAuthFailure() {
        showToast(R.string.text_auth_failure)
    }


    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, UserAuthActivity::class.java)
            activity.startActivity(intent)
        }
    }
}