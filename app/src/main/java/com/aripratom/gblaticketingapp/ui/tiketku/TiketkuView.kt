package com.aripratom.gblaticketingapp.ui.tiketku

import com.aripratom.gblaticketingapp.data.TiketPengguna

interface TiketkuView {
    fun showTiket(ticket: List<TiketPengguna?>)
    fun emptyPemesasnan()
    fun notEmptyPemesanan()
    fun showLoading()
    fun hideLoading()
}