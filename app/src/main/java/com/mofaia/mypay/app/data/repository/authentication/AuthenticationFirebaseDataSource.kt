package com.mofaia.mypay.app.data.repository.authentication

import com.google.firebase.auth.FirebaseAuth

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

    override fun createAccount(email: String, password: String, onSuccess: () -> Unit, onError: () -> Unit) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful) {
                onSuccess()
            }else {
                onError()
            }
        }

    }

    override fun getCurrentUser() {
        firebaseAuth.currentUser
    }

}