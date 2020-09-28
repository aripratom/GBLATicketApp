package com.aripratom.gblaticketingapp.ui.tiketku

import com.aripratom.gblaticketingapp.data.TiketRFID
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TiketkuDetailPresenter(private val view: TiketkuDetailView) {

    private val db = FirebaseDatabase.getInstance().reference

    fun getRFID() {
        //view.showLoading()
        db.child("tiketRFID").orderByChild("rfid")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val ticket: MutableList<TiketRFID?> = mutableListOf()
                    try {
                        ticket.clear()
                        p0.children.forEach {
                            val t = it.getValue(TiketRFID::class.java)
                            ticket.add(t)
                            //Log.d("TPPPPPPPPPP",t.toString())

                        }
                        //Log.d("TPPPPPPPPPPPPP",ticket.toString())
                        ticket.reverse()
                        view.showData(ticket)
                        //Log.d("TPDDDDDDDDDD",ticket.toString())
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