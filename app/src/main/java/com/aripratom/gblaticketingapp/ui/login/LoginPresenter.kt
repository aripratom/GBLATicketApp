package com.aripratom.gblaticketingapp.ui.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter (private val view: LoginView) {

    private val auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String) {
        try {
            view.showLoading()
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    view.hideLoading()
                    view.loginSuccess()
                    Log.d(email,"AAAAAAAAAA")
                }
                .addOnFailureListener {
                    view.hideLoading()
                    view.loginFail()
                    Log.d("EEEEEEEEEE",it.message)
                }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}