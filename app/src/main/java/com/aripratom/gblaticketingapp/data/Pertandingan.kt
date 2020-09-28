package com.aripratom.gblaticketingapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pertandingan(
    //val id: Int? = null,
    val idPertandingan: Int? = null,
    val hari: String? = null,
    val jamPertandingan: String? = null,
    val match: String? = null,
    val tanggal: String? = null,
    val tanggalFull: String? = null,
    //jval tanggalPertandingan : Long? =null,
    val logoLink: String? = null,
    val logoHome: String? = null,
    val logoAway: String? = null,
    val teamHome: String? = null,
    val teamAway: String? = null,
    val idKursi: String? = "https://gbla-ticket-data.firebaseio.com/noKursi",
    val status: String? = null
) : Parcelable