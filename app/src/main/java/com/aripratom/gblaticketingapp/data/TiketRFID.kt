package com.aripratom.gblaticketingapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TiketRFID(
    val tanggal: String? = null,
    val slotKursi: String? = null,
    val rfid: String? = null
) : Parcelable