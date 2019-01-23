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
import com.mofaia.mypay.app.extension.showToast
import org.koin.android.viewmodel.ext.android.viewModel

class CreateAccountActivity : AppCompatActivity() {

    private val  viewModel: CreateAccountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = setContentView<ActivityCreateAccountBinding>(
                this, R.layout.activity_create_account)
        contentView.viewModel = viewModel
        handleUI()
    }

    private fun handleUI() {
        configureToolbar()
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
    }

    private fun handleAccountCreatedFailure() {
        showToast(R.string.text_account_created_failure)
    }

    private fun configureToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        handleHomeBtnPressed(item?.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun handleHomeBtnPressed(itemId: Int?) {
        if(android.R.id.home == itemId) {
            finish()
        }
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, CreateAccountActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
