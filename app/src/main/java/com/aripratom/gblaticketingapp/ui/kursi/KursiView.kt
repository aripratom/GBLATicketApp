package com.aripratom.gblaticketingapp.ui.kursi

import com.aripratom.gblaticketingapp.data.Kursi
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.TiketPengguna

interface KursiView{
    //fun showLoading()
    //fun hideLoading()
    fun showKursiList(data: List<Kursi?>)
    fun showBooked(data: List<TiketPengguna?>)
    //fun onItemClickCallback(data: Kursi)
    //fun showPertandingan (data : List<Pertandingan?>)
}