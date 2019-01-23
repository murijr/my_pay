package com.mofaia.mypay.app.extension

import android.app.Activity
import android.widget.Toast
import android.widget.Toast.makeText

fun Activity.showToast(resourceId: Int) {

    makeText(this, resourceId, Toast.LENGTH_LONG).show()

}