package com.aripratom.gblaticketingapp.ui.signup

import com.aripratom.gblaticketingapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpPresenter(private val view: SignUpView) {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().reference

    fun getCity() {
        view.showCity()
    }

    fun signUp(email: String, password: String, name: String, city: String, phone: String) {
        try {
            view.showLoading()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    view.hideLoading()
                    auth.currentUser?.sendEmailVerification()
                    db.child("user")
                        .child(auth.currentUser!!.uid)
                        .setValue(
                            User(
                                auth.currentUser!!.uid,
                                name,
                                city,
                                email,
                                phone,
                                rfid = ""
                            )
                        )
                    view.signUpSuccess()
                }
                .addOnFailureListener {
                    view.hideLoading()
                    view.signUpFail()
                }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}