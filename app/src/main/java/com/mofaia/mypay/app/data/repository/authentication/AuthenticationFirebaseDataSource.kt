package com.mofaia.mypay.app.data.repository.authentication

import com.google.firebase.auth.FirebaseAuth
import com.mofaia.mypay.app.data.entity.User
import com.mofaia.mypay.app.extension.toUser

class AuthenticationFirebaseDataSource(private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()): AuthenticationDataSource {

    override fun login(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                onSuccess()
            }else {
                onError()
            }
        }

    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun createAccount(email: String, password: String, onSuccess: (User) -> Unit, onError: () -> Unit) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                onSuccess(firebaseAuth.currentUser!!.toUser())
            }else {
                onError()
            }
        }

    }

    override fun getCurrentUser() =
        firebaseAuth.currentUser?.toUser()

}