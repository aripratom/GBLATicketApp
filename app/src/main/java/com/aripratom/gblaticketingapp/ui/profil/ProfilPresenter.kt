package com.aripratom.gblaticketingapp.ui.profil

import android.util.Log
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfilPresenter(private val view: ProfilView) {

    private val auth = FirebaseAuth.getInstance()
    private val uid = auth.currentUser?.uid!!
    private val db = FirebaseDatabase.getInstance().reference

    fun getUserData(){
        //view.showLoading()
        db.child("user").child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
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

    fun getTiket() {
        view.showLoading()
        db.child("tiketPengguna").orderByChild("id").equalTo(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val ticket: MutableList<TiketPengguna?> = mutableListOf()
                    try {
                        ticket.clear()
                        p0.children.forEach {

                            val t = it.getValue(TiketPengguna::class.java)
                            ticket.add(t)
                            Log.d("tttttttttttt",t.toString())

                        }
                        ticket.reverse()
                        view.showTiket(ticket)
                        view.hideLoading()

                        if (ticket.isEmpty()){
                            view.emptyPemesasnan()
                        }
                        else {
                            view.notEmptyPemesanan()
                        }

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }


    fun logout() {
        auth.signOut().also {
            view.doLogout()
            db.child("user").child(uid).child("tokenId").removeValue()
        }
    }
}