package com.aripratom.gblaticketingapp.ui.pemesananKonfirmasi

import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.data.User

interface PemesananKonfirmasiView {
    fun showLoading()
    fun hideLoading()
    fun updateSuccess()
    fun updateFailed()
    fun showPemesanan(ticket: List<Transaksi?>)
    fun eventNotFound()

}