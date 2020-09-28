package com.aripratom.gblaticketingapp.ui.pertandingan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.util.gone
import com.aripratom.gblaticketingapp.util.visible
import kotlinx.android.synthetic.main.fragment_pertandingan.*
import org.jetbrains.anko.support.v4.onRefresh

class PertandinganFragment : Fragment(), PertandinganView {


    private lateinit var presenter: PertandinganPresenter
    private lateinit var adapter: PertandinganAdapter
    private val pertandingan = mutableListOf<Pertandingan?>()
    private val tiket = mutableListOf<TiketPengguna?>()
    private lateinit var tiketPengguna: TiketPengguna

    override fun showData(pertandingan: List<Pertandingan?>) {
        this.pertandingan.clear()
        this.pertandingan.addAll(pertandingan)
        adapter.notifyDataSetChanged()

    }

    override fun showLoading() {
        srlPertandingan.isRefreshing = true
    }

    override fun hideLoading() {
        srlPertandingan.isRefreshing = false
    }

    /*
    override fun showMyTicket(tiketPengguna: List<TiketPengguna?>) {
        tiket.addAll(tiketPengguna)
    }

     */

    override fun emptyPertandingan() {
        rv_pertandingan.gone()
        tvEmptyPertandingan.visible()
    }

    override fun notEmptyPertandingan() {
        rv_pertandingan.visible()
        tvEmptyPertandingan.gone()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pertandingan, container, false)
        setHasOptionsMenu(false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = PertandinganPresenter(this)
        presenter.getPertandingan()

        setupRecyclerView()
        srlPertandingan.onRefresh { presenter.getPertandingan() }
    }

    private fun setupRecyclerView() {
        adapter = PertandinganAdapter(pertandingan)
        rv_pertandingan.setHasFixedSize(true)
        rv_pertandingan.layoutManager = LinearLayoutManager(context)
        rv_pertandingan.adapter = adapter


    }

    override fun onResume() {
        super.onResume()
        presenter.getPertandingan()
    }



}
