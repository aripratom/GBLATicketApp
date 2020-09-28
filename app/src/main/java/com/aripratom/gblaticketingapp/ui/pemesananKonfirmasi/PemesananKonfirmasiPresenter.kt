package com.aripratom.gblaticketingapp.ui.pemesananKonfirmasi

import android.util.Log
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.TiketRFID
import com.aripratom.gblaticketingapp.data.Transaksi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PemesananKonfirmasiPresenter(private val view: PemesananKonfirmasiView) {
    private val db = FirebaseDatabase.getInstance().reference
    private val user = FirebaseAuth.getInstance().currentUser!!.uid
    private val tid = db.child("tiketRFID").push().key


    fun konfirmasiPemesanan(tiketPengguna: TiketPengguna){
        try {
            view.showLoading()
            db.child("tiketPengguna").child(tiketPengguna.idTransaksi!!).setValue(tiketPengguna)
                .addOnSuccessListener {
                    view.hideLoading()
                    view.updateSuccess()
                }
                .addOnFailureListener {
                    view.hideLoading()
                    view.updateFailed()
                }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }

    fun konfirmasiTiket(tiketRFID: TiketRFID, idTransaksi: String){
        try {
            view.showLoading()
            db.child("tiketRFID").child(idTransaksi).setValue(tiketRFID)
                .addOnSuccessListener {
                    view.hideLoading()
                    view.updateSuccess()
                }
                .addOnFailureListener {
                    view.hideLoading()
                    view.updateFailed()
                }
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }
    }



    fun getPemesanan(idTransaksi: String) {
        view.showLoading()
        db.child("transaksi").orderByChild("idTransaksi").equalTo(idTransaksi)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val ticket: MutableList<Transaksi?> = mutableListOf()
                    try {
                        ticket.clear()
                        p0.children.forEach {

                            val t = it.getValue(Transaksi::class.java)
                            ticket.add(t)
                            Log.d("tttttttttttt",t.toString())

                        }
                        ticket.reverse()
                        view.showPemesanan(ticket)
                        view.hideLoading()

                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })
    }

    fun deleteTransaksi(idTransaksi: String) {
        try {
            db.child("transaksi").child(idTransaksi).removeValue().addOnSuccessListener {
                view.eventNotFound()
                //db.child("join").child(eid).removeValue()
            }
        } finally { }
    }

    fun onClose(transaksi: Transaksi) {
        db.child("transaksi").child(transaksi.idTransaksi!!).removeValue()
    }


}