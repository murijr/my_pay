package com.mofaia.mypay.app.extension

import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.databinding.DataBindingUtil.setContentView
import com.mofaia.mypay.app.BR

fun AppCompatActivity.showToast(resourceId: Int) {
    makeText(this, resourceId, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.configureToolbar() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
}

fun <T :ViewDataBinding> AppCompatActivity.setContentView(viewModel: ViewModel, layoutRes: Int) {
    val contentView = setContentView<T>(this, layoutRes)
    contentView.setVariable(BR.viewModel, viewModel)
}