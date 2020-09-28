package com.aripratom.gblaticketingapp.ui.order

import android.util.Log
import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderPresenter(private val view: OrderView) {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().reference
    private val tid = db.child("transaksi").push().key
    private val uid = auth.currentUser?.uid

    fun setID() {
        view.setID(tid, uid)
    }

    fun postIdTransaksi(idTransaksi: Transaksi) {
        try {
            db.child("transaksi").child(tid!!).setValue((idTransaksi))
                .addOnSuccessListener {
                    Log.d("berhasil", db.toString())
                }
                .addOnFailureListener {
                    //Log.d("gagal", it.localizedMessage)
                }


        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("ERROR", db.toString())
        }
    }

    fun getUserData() {
        //view.showLoading()
        db.child("user").child(uid!!).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val user = p0.getValue(User::class.java)
                    //view.hideLoading()
                    view.showData(user)
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })

    }


}