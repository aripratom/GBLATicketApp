package com.aripratom.gblaticketingapp.ui.pemesanan

import android.util.Log
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.Transaksi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PemesananPresenter(private val view: PemesananView) {
    private val auth = FirebaseAuth.getInstance()
    private val uid = auth.currentUser?.uid!!
    private val db = FirebaseDatabase.getInstance().reference

    fun getPemesanan() {
        view.showLoading()
        db.child("transaksi").orderByChild("id").equalTo(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val transaction: MutableList<Transaksi?> = mutableListOf()
                    try {
                        transaction.clear()
                        p0.children.forEach {

                            val t = it.getValue(Transaksi::class.java)
                            transaction.add(t)
                            Log.d("tttttttttttt",t.toString())

                        }
                        transaction.reverse()
                        view.hideLoading()
                        view.showPemesanan(transaction)

                        if (transaction.isEmpty()) view.emptyPemesasnan()
                        else view.notEmptyPemesanan()


                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }

    fun getTicketData() {
        //view.showLoading()
        FirebaseDatabase.getInstance().reference.child("tiketPengguna")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val tiket = mutableListOf<TiketPengguna?>()
                    try {
                        tiket.clear()
                        p0.children.forEach {
                            val t = it.getValue(TiketPengguna::class.java)
                            tiket.add(t)
                            Log.d("tttttttttttt", t.toString())

                        }
                        view.showData(tiket)
                        Log.d("tttttttttttt", tiket.toString())

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }


}