package com.aripratom.gblaticketingapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TiketPengguna(
    val id: String? = null,
    val idTransaksi: String? = null,
    val idKursi: String? = null,
    val idPertandingan: Int? = null,
    val match: String? = null,
    val hari: String? = null,
    val tanggal: String? = null,
    val tanggalFull: String? = null,
    val jamPertandingan: String? = null,
    val slotKursi: String? = null,
    val hargaTiket: String? = null,
    val rfid: String? = null,
    val email: String? = null,
    val logoHome: String? = null,
    val logoAway: String? = null,
    val teamHome: String? = null,
    val teamAway: String? = null
) : Parcelable