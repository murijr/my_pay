package com.mofaia.mypay.app.feature.userAuth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mofaia.mypay.app.R

class UserAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_auth)
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, UserAuthActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
