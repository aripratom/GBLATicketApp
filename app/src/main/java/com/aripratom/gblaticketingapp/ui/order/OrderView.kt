package com.aripratom.gblaticketingapp.ui.order

import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.User

interface OrderView {
    fun setID(tid: String?, uid: String?)
    fun showData(user: User?)
    fun dialog()
  //  fun showPertandingan(pertandingan: List<Pertandingan?>)

}