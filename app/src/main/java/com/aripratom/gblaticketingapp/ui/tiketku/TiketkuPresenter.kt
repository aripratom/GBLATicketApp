package com.aripratom.gblaticketingapp.ui.tiketku

import android.util.Log
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TiketkuPresenter(private val view: TiketkuView) {
    private val auth = FirebaseAuth.getInstance()
    private val uid = auth.currentUser?.uid!!
    private val db = FirebaseDatabase.getInstance().reference

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
                        view.hideLoading()
                        view.showTiket(ticket)

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

}