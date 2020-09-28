package com.aripratom.gblaticketingapp.ui.kursi

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.Kursi
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.ui.order.OrderActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_kursi.*
import org.jetbrains.anko.startActivity

class KursiActivity : AppCompatActivity(), KursiView {

    private lateinit var presenter: KursiPresenter
    private lateinit var adapter: KursiAdapter
    private val kursi = mutableListOf<Kursi?>()
    private lateinit var pertandingan: Pertandingan
    private val booked = mutableListOf<TiketPengguna?>()

    override fun showKursiList(data: List<Kursi?>) {
        this.kursi.clear()
        this.kursi.addAll(data)
        adapter.notifyDataSetChanged()
        Log.d("CCCCCCCCCCCC", kursi.toString())
    }

    override fun showBooked(data: List<TiketPengguna?>) {
        booked.addAll(data)
    }
/*
    override fun showLoading() {
        srlKursi.isRefreshing = true
    }

    override fun hideLoading() {
        srlKursi.isRefreshing = false
    }
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kursi)

        supportActionBar?.title = getString(R.string.pilih_kursi)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = KursiPresenter(this)
        presenter.getKursiList()
        presenter.getBookedChair()
        pertandingan = intent.getParcelableExtra("pertandingan")
        getPertandingan(pertandingan)

        showRecycleList()
        //srlKursi.onRefresh { presenter.getKursiList() }
    }

    override fun onResume() {
        super.onResume()
        presenter.getKursiList()
    }


    private fun showRecycleList() {
        adapter = KursiAdapter(kursi, booked, pertandingan.idPertandingan!!)
        rv_kursi.setHasFixedSize(true)
        rv_kursi.layoutManager = GridLayoutManager(this, 27)
        rv_kursi.adapter = adapter

        //adapter = KursiAdapter(kursi)
        adapter.setOnItemClickCallback(object : KursiAdapter.OnItemClickCallback {
            override fun onItemClickCallback(data: Kursi) {

                startActivity<OrderActivity>("kursi" to data, "pertandingan" to pertandingan)
            }
        })
    }

    private fun getPertandingan(pertandingan: Pertandingan?) {
        Glide.with(this).load(pertandingan?.logoHome)
            .placeholder(R.mipmap.ic_launcher_round).into(imgHomeTeamKursi)

        Glide.with(this).load(pertandingan?.logoAway)
            .placeholder(R.mipmap.ic_launcher_round).into(imgAwayTeamKursi)

        tvDayKursi.text = pertandingan?.hari
        tvTimeKursi.text = pertandingan?.jamPertandingan
        tvDateKursi.text = pertandingan?.tanggalFull
        tvHomeTeamKursi.text = pertandingan?.teamHome
        tvAwayTeamKursi.text = pertandingan?.teamAway

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
