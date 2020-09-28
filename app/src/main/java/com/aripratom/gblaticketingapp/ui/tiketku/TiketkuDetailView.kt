package com.aripratom.gblaticketingapp.ui.tiketku

import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.TiketRFID

interface TiketkuDetailView {
    fun showData(rfid: List<TiketRFID?>)
}