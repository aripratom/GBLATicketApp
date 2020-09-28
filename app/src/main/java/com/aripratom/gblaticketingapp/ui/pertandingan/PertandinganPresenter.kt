package com.aripratom.gblaticketingapp.ui.pertandingan

import android.util.Log
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.util.DateUtil.locale
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class PertandinganPresenter(private val view: PertandinganView) {

    private val db = FirebaseDatabase.getInstance().reference.child("pertandingan")

    fun getPertandingan() {
        view.showLoading()
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val match = mutableListOf<Pertandingan?>()
                    p0.children.forEach {
                        val data = it.getValue(Pertandingan::class.java)
                        match.add(data)
                    }

                    view.hideLoading()
                    view.showData(match)
                    Log.d("BBBBBBB",match.toString())

                    if (match.size == 0) view.emptyPertandingan()
                    else view.notEmptyPertandingan()

                    //Log.d("EEEEEEEEEE",match)
                   // checkExpire(tanggal)
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


}