package com.aripratom.gblaticketingapp.ui.pemesanan

import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.Transaksi

interface PemesananView{
    fun showPemesanan(transaction: List<Transaksi?>)
    fun showData(ticket: List<TiketPengguna?>)
    fun emptyPemesasnan()
    fun notEmptyPemesanan()
    fun showLoading()
    fun hideLoading()
}