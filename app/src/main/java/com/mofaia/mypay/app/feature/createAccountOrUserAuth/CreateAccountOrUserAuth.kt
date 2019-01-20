package com.mofaia.mypay.app.feature.createAccountOrUserAuth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.databinding.ActivityCreateAccountOrUserAuthBinding

class CreateAccountOrUserAuth : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityCreateAccountOrUserAuthBinding>(this
                , R.layout.activity_create_account_or_user_auth)
    }
}
