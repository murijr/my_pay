package com.mofaia.mypay.app.extension
import com.google.firebase.auth.FirebaseUser
import com.mofaia.mypay.app.data.entity.User

fun FirebaseUser.toUser() = User(uid, email.orEmpty())