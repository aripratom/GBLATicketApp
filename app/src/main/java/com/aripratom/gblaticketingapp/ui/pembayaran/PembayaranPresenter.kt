package com.aripratom.gblaticketingapp.ui.pembayaran

import android.util.Log
import com.aripratom.gblaticketingapp.data.Kursi
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.Transaksi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PembayaranPresenter(private val view: PembayaranView) {

    private val db = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()
    private val tid = db.child("transaksi").push().key
    private val uid = auth.currentUser?.uid
    private val kid = db.child("noKursi")
    private val pid = db.child("pertandingan")

}
/*
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


    fun getPertandingan(pertandingan: Int?) {
        Log.d("PERTANDINGAN",pertandingan.toString())
        db.child("pertandingan").child(pertandingan.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val match = mutableListOf<Pertandingan?>()
                    //p0.children.forEach {
                    val m = p0.getValue(Pertandingan::class.java)
                    match.add(m)
                    Log.d("CCCCCCCCCCCCCCCCC",m.toString())
                    Log.d("DDDDDDDDDDDDD",pertandingan.toString())
                    // }
                    val data = match.filter {
                        it!!.idPertandingan == pertandingan
                    }
                   // view.showTransaksiId(data)
                    Log.d("TTTTTTTTTTT",data.toString())

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    /*
    fun getPertandingan() {
        pid.child("pertandingan").orderByChild("match").equalTo("match").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val match = mutableListOf<Pertandingan?>()
                    p0.children.forEach {
                        val p = it.getValue(Pertandingan::class.java)
                        match.add(p)
                        view.setMatch(match)
                    }

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
*/
/*
    fun getKursiList(){
        kid.child("noKursi").orderByChild("idKursi").equalTo("idKursi").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val chair = mutableListOf<Kursi?>()
                    p0.children.forEach {
                        val c = it.getValue(Kursi::class.java)
                        chair.add(c)
                        view.setKursi(chair)
                    }

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    */

}
 */
