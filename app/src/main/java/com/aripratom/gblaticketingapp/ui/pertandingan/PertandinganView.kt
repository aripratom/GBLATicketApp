package com.aripratom.gblaticketingapp.ui.pertandingan

import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.TiketPengguna

interface PertandinganView {
    fun showLoading()
    fun hideLoading()
    fun showData(pertandingan: List<Pertandingan?>)
    //fun showMyTicket(tiketPengguna: List<TiketPengguna?>)
    fun emptyPertandingan()
    fun notEmptyPertandingan()

    //fun onItemClicked(data: Pertandingan)

}