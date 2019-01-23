package com.mofaia.mypay.app.feature.authentication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil.setContentView
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.databinding.ActivityCreateAccountBinding
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
