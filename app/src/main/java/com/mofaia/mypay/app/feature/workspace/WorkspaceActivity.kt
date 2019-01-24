package com.mofaia.mypay.app.feature.workspace

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mofaia.mypay.app.R

class WorkspaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, WorkspaceActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
