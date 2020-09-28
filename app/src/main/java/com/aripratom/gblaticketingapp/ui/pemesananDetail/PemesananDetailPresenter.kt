package com.aripratom.gblaticketingapp.ui.pemesananDetail

import android.util.Log
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.Transaksi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PemesananDetailPresenter(private val view: PemesananDetailView) {
    private val db = FirebaseDatabase.getInstance().reference

    private val auth = FirebaseAuth.getInstance()
    private val uid = auth.currentUser?.uid!!


    fun getTiket() {
        //view.showLoading()
        db.child("tiketPengguna").orderByChild("id").equalTo(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val ticket: MutableList<TiketPengguna?> = mutableListOf()
                    try {
                        ticket.clear()
                        p0.children.forEach {
                            val t = it.getValue(TiketPengguna::class.java)
                            ticket.add(t)
                            Log.d("TRRRRRRRRRR",t.toString())

                        }
                        Log.d("TCCCCCCCCCCCC",ticket.toString())
                        ticket.reverse()
                        view.showTiket(ticket)
                        Log.d("TDDDDDDD",ticket.toString())
                        //view.hideLoading()

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }


    fun getTiketIdPertandingan() {
        //view.showLoading()
        db.child("tiketPengguna").orderByChild("idPertandingan")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val ticket: MutableList<TiketPengguna?> = mutableListOf()
                    try {
                        ticket.clear()
                        p0.children.forEach {
                            val t = it.getValue(TiketPengguna::class.java)
                            ticket.add(t)
                            Log.d("TPPPPPPPPPP",t.toString())

                        }
                        Log.d("TPPPPPPPPPPPPP",ticket.toString())
                        ticket.reverse()
                        view.showData(ticket)
                        Log.d("TPDDDDDDDDDD",ticket.toString())
                        //view.hideLoading()

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }

    fun getTiketPengguna() {
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val match = mutableListOf<TiketPengguna?>()
                    p0.children.forEach {
                        val data = it.getValue(TiketPengguna::class.java)
                        match.add(data)
                    }
                    view.showData(match)
                    Log.d("BBBBBBB",match.toString())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    Log.d("GGGGGGG",p0.toString())
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.d("GGGGGG",p0.toString())
            }
        })
    }


    fun getIdPertandingan(id: String) {
        //view.showLoading()
        db.child("tiketPengguna").orderByChild("id").equalTo(id)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val ticket: MutableList<TiketPengguna?> = mutableListOf()
                    try {
                        ticket.clear()
                        p0.children.forEach {
                            val t = it.getValue(TiketPengguna::class.java)
                            ticket.add(t)
                            Log.d("PERTANDINGAN",t.toString())

                        }
                        ticket.reverse()
                        //view.showTicket(ticket)
                        Log.d("PERTANDINGAN",ticket.toString())
                        //view.hideLoading()

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }

}