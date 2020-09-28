package com.aripratom.gblaticketingapp.util

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.aripratom.gblaticketingapp.data.TiketRFID
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object FirebaseUtil {

    private val db = FirebaseDatabase.getInstance().reference
    private val auth = FirebaseAuth.getInstance()

    fun getTiketRFIDdata(ctx: Context, rfid: String?,tanggal:String?,tvStatusTiket: TextView) {
        db.child("tiketRFID").child(rfid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val tiket = p0.getValue(TiketRFID::class.java)
                    Log.d("tiiiikkkk",tiket.toString())
                    tvStatusTiket.text = tiket?.slotKursi

                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }


}