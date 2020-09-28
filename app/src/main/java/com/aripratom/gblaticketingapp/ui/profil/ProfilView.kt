package com.aripratom.gblaticketingapp.ui.profil

import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.User

interface ProfilView {
    fun showData(user: User?)
    fun doLogout()
    fun showTiket(ticket: List<TiketPengguna?>)
    fun emptyPemesasnan()
    fun notEmptyPemesanan()
    fun showLoading()
    fun hideLoading()

}