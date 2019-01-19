package com.mofaia.mypay.feature.goToCreateAccountOrUserAuth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.mofaia.mypay.R
import com.mofaia.mypay.databinding.ActivityGoToCreateAccountOrUserAuthBinding

class GoToCreateAccountOrUserAuth : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityGoToCreateAccountOrUserAuthBinding>(this
                , R.layout.activity_go_to_create_account_or_user_auth)
    }
}
