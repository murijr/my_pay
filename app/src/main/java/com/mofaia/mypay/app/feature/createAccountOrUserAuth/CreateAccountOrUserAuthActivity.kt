package com.mofaia.mypay.app.feature.createAccountOrUserAuth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.databinding.ActivityCreateAccountOrUserAuthBinding
import com.mofaia.mypay.app.feature.createAccount.CreateAccountActivity
import kotlinx.android.synthetic.main.activity_create_account_or_user_auth.*

class CreateAccountOrUserAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityCreateAccountOrUserAuthBinding>(this
                , R.layout.activity_create_account_or_user_auth)
        handleUI()
    }

    private fun handleUI() {
        observeBtnCreateAccountClick()
    }

    private fun observeBtnCreateAccountClick() {
        btn_create_acount.setOnClickListener { CreateAccountActivity.start(this) }
    }

}
