package com.aripratom.gblaticketingapp.ui.pemesananDetail

import com.aripratom.gblaticketingapp.data.TiketPengguna

interface PemesananDetailView {
    fun showData(tiket : List<TiketPengguna?>)
    fun showTiket(tiket : List<TiketPengguna?>)
}