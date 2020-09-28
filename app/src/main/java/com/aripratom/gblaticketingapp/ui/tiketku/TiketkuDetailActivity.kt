package com.aripratom.gblaticketingapp.ui.tiketku

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.TiketRFID
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tiket_ku_detail.*
import org.jetbrains.anko.textColorResource
import org.jetbrains.anko.textResource

class TiketkuDetailActivity : AppCompatActivity(), TiketkuDetailView {
    private lateinit var tiket: TiketPengguna
    private lateinit var tiketRFIDdata: TiketRFID
    private lateinit var presenter: TiketkuDetailPresenter
    private var tiketRFID: TiketRFID? = null

    override fun showData(rfid: List<TiketRFID?>) {
        val ticket = rfid.filter {
            it!!.rfid == tiket.rfid && it.tanggal == tiket.tanggal
        }
        if (ticket.size == 1) {
            if (ticket[0]!!.slotKursi.toString() == "1") {
                tvStatusTiket.textResource = R.string.tiket_tersedia
                tvStatusTiket.textColorResource = R.color.green
            } else if (ticket[0]!!.slotKursi.toString() == "0") {
                tvStatusTiket.textResource = R.string.tiket_digunakan
                tvStatusTiket.textColorResource = R.color.red
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiket_ku_detail)

        supportActionBar?.title = getString(R.string.tiket)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = TiketkuDetailPresenter(this)

        tiket = intent.getParcelableExtra("tiketku")
        getTiket(tiket)

        presenter.getRFID()

    }

    private fun getTiket(tiket: TiketPengguna?){
        Glide.with(this).load(tiket?.logoHome)
            .placeholder(R.mipmap.ic_launcher_round).into(imgHomeTeamTiket)

        Glide.with(this).load(tiket?.logoAway)
            .placeholder(R.mipmap.ic_launcher_round).into(imgAwayTeamTiket)

        tvHomeTeamTiket.text = tiket?.teamHome
        tvAwayTeamTiket.text = tiket?.teamAway
        tvDateTiket.text = tiket?.tanggalFull
        idTransaksiTiket.text = tiket?.idTransaksi
        pertandinganTiket.text = tiket?.match
        noKursiTiket.text = tiket?.idKursi
        tvDayTiket.text = tiket?.hari
        tvTimeTiket.text = tiket?.jamPertandingan
        tvKursiTiketku.text = tiket?.idKursi
        tvNameMatch.text = tiket?.match
        emailTiket.text = tiket?.email
        /*
        if(tiket?.slotKursi == "1") {
            tvStatusTiket.text = "Tiket Siap Digunakan"
            tvStatusTiket.setTextColor(getResources().getColor(R.color.green))
        } else if(tiket?.slotKursi == "0"){
            tvStatusTiket.text = "Tiket Telah Digunakan"
            tvStatusTiket.setTextColor(getResources().getColor(R.color.red))
        }*/



        //presenter.getTiketRFIDdata(tiket?.rfid,tiket?.tanggal)
    }

    override fun onResume() {
        super.onResume()
        getTiket(tiket)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
