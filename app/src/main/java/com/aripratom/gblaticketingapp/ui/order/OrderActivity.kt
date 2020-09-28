package com.aripratom.gblaticketingapp.ui.order

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.Kursi
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.data.User
import com.aripratom.gblaticketingapp.ui.pembayaranMenunggu.MenungguPembayaranActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_order.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class OrderActivity : AppCompatActivity(), OrderView {

    private lateinit var pertandingan: Pertandingan
    private lateinit var transaksi: Transaksi
    private lateinit var kursi: Kursi
    private lateinit var presenter: OrderPresenter
    private var tid: String? = null
    private var uid: String? = null
    private var user: User? = null
    private var rfid: String? = null
    private var email: String? = null


    override fun setID(tid: String?, uid: String?) {
        this.tid = tid
        this.uid = uid
    }

    override fun showData(user: User?) {
        this.user = user
        rfid = user?.rfid
        email = user?.email

        Log.d("RFFFFFFFFF", rfid.toString())
    }

    override fun dialog() {
        viewDialog(R.string.dialog_bayar) {
            presenter.postIdTransaksi(
                Transaksi(
                    id = uid,
                    idTransaksi = tid,
                    idKursi = kursi.idKursi,
                    idPertandingan = pertandingan.idPertandingan,
                    match = pertandingan.match,
                    tanggal = pertandingan.tanggal,
                    tanggalFull = pertandingan.tanggalFull,
                    jamPertandingan = pertandingan.jamPertandingan,
                    hargaTiket = "102500",
                    rfid = rfid!!,
                    email = email!!,
                    logoHome = pertandingan.logoHome,
                    logoAway = pertandingan.logoAway,
                    teamHome = pertandingan.teamHome,
                    teamAway = pertandingan.teamAway,
                    hari = pertandingan.hari

                )
            )
            startActivity<MenungguPembayaranActivity>("idTransaksi" to pertandingan.tanggalFull)
        }
    }


    private fun viewDialog(title: Int, action: () -> Unit) {
        alert {
            titleResource = title
            isCancelable = false
            yesButton { action() }
            noButton { it.dismiss() }
        }.show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        supportActionBar?.title = getString(R.string.ringkasan_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        kursi = intent.getParcelableExtra("kursi")
        getNoKursi(kursi)

        pertandingan = intent.getParcelableExtra("pertandingan")
        getPertandingan(pertandingan)

        presenter = OrderPresenter(this)
        presenter.setID()
        presenter.getUserData()


        // startActivity<MenungguPembayaranActivity>("idTransaksi" to tid) }

        btnBayarOrder.onClick {
            dialog()
        }

    }

    private fun getNoKursi(kursi: Kursi?) {
        noKursiOrder.text = kursi?.idKursi

    }

    private fun getPertandingan(pertandingan: Pertandingan?) {
        Glide.with(this).load(pertandingan?.logoHome)
            .placeholder(R.mipmap.ic_launcher_round).into(imgHomeTeamOrder)

        Glide.with(this).load(pertandingan?.logoAway)
            .placeholder(R.mipmap.ic_launcher_round).into(imgAwayTeamOrder)

        tvDayOrder.text = pertandingan?.hari
        tvTimeOrder.text = pertandingan?.jamPertandingan
        tvDateOrder.text = pertandingan?.tanggalFull
        tvHomeTeamOrder.text = pertandingan?.teamHome
        tvAwayTeamOrder.text = pertandingan?.teamAway
        Log.d("TTTTTTTTTTT", pertandingan?.teamHome.toString())

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
