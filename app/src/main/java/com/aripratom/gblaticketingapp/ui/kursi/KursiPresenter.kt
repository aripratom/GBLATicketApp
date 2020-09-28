package com.aripratom.gblaticketingapp.ui.kursi

import android.util.Log
import com.aripratom.gblaticketingapp.data.Kursi
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class KursiPresenter(private val view: KursiView) {

    private val db = FirebaseDatabase.getInstance().reference.child("noKursi")
    fun getKursiList() {
        //view.showLoading()
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val chair = mutableListOf<Kursi?>()
                    p0.children.forEach {
                        val data = it.getValue(Kursi::class.java)
                        chair.add(data)
                    }
          //          view.hideLoading()
                    view.showKursiList(chair)
                    Log.d("SSSSSSSSSSS", chair.toString())

                } catch (ex: Exception) {
                    ex.printStackTrace()
                    //   Log.d("SSSSSSSSSSS",p0.toString())
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                //Log.d("SSSSSSSSSSS",p0.toString())

            }
        })
    }

    fun getBookedChair() {
        //view.showLoading()
        FirebaseDatabase.getInstance().reference.child("tiketPengguna")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val kursi = mutableListOf<TiketPengguna?>()
                    try {
                        kursi.clear()
                        p0.children.forEach {
                            val t = it.getValue(TiketPengguna::class.java)
                            kursi.add(t)
                            Log.d("tttttttttttt", t.toString())

                        }
                        view.showBooked(kursi)
                        Log.d("tttttttttttt", kursi.toString())

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }


}
