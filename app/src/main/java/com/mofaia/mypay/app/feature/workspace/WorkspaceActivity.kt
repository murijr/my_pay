package com.mofaia.mypay.app.feature.workspace

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.databinding.ActivityWorkspaceBinding
import com.mofaia.mypay.app.extension.setContentView
import org.koin.android.viewmodel.ext.android.viewModel

class WorkspaceActivity : AppCompatActivity() {

    val viewModel: WorkspaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityWorkspaceBinding>(viewModel, R.layout.activity_workspace)
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, WorkspaceActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
