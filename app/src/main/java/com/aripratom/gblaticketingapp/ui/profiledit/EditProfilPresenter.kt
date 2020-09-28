package com.aripratom.gblaticketingapp.ui.profiledit

import android.net.Uri
import com.aripratom.gblaticketingapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.lang.Exception

class EditProfilPresenter(private val view: EditProfilView) {

    private val current = FirebaseAuth.getInstance().currentUser
    private val db = FirebaseDatabase.getInstance().reference.child("user").child(current!!.uid)
    private val storage = FirebaseStorage.getInstance().reference.child(current!!.uid + ".png")

    fun getData(user: User?) {
        view.showData(user)
    }

    fun updateProfile(newName: String, newCity: String, newMail: String, newPhone: String, newPass: String?) {
        try {
            view.showLoading("Updating Profile")

            if (newMail != current?.email) current?.updateEmail(newMail)
            if (newPass != null) current?.updatePassword(newPass)

            db.let {
                it.child("name").setValue(newName)
                it.child("city").setValue(newCity)
                it.child("email").setValue(newMail)
                it.child("phone").setValue(newPhone)
            }
                .addOnSuccessListener {
                    view.hideLoading()
                    view.showUpdated("Profile Updated")
                }
                .addOnFailureListener {
                    view.hideLoading()
                    view.showFail("Failed to update your profile")
                }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}