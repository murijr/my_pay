package com.mofaia.mypay.app.feature.createAccountOrUserAuth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.databinding.ActivityCreateAccountOrUserAuthBinding
import com.mofaia.mypay.app.feature.createAccount.CreateAccountActivity
import com.mofaia.mypay.app.feature.userAuth.UserAuthActivity
import com.mofaia.mypay.app.feature.workspace.WorkspaceActivity
import kotlinx.android.synthetic.main.activity_create_account_or_user_auth.*
import org.koin.android.viewmodel.ext.android.viewModel

class CreateAccountOrUserAuthActivity : AppCompatActivity() {

    val viewModel: CreateAccountOrUserAuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityCreateAccountOrUserAuthBinding>(this
                , R.layout.activity_create_account_or_user_auth)
        handleUserLoggedIn()
        handleUI()
    }

    private fun handleUI() {
        observeBtnCreateAccountClick()
        observeBtnUserAuthClick()
    }

    private fun handleUserLoggedIn() {
        if(viewModel.user != null) {
            finishAffinity()
            WorkspaceActivity.start(this)
        }
    }

    private fun observeBtnCreateAccountClick() {
        btn_create_acount.setOnClickListener { CreateAccountActivity.start(this) }
    }

    private fun observeBtnUserAuthClick() {
        btn_login.setOnClickListener { UserAuthActivity.start(this) }
    }


}
