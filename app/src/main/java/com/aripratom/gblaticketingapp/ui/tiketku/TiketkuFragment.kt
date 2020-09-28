package com.aripratom.gblaticketingapp.ui.tiketku

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.util.gone
import com.aripratom.gblaticketingapp.util.visible
import kotlinx.android.synthetic.main.fragment_tiketku.*
import org.jetbrains.anko.support.v4.onRefresh

class TiketkuFragment : Fragment(), TiketkuView {
    private lateinit var presenter: TiketkuPresenter
    private lateinit var adapter: TiketkuAdapter
    private val tiket: MutableList<TiketPengguna?> = mutableListOf()

    override fun showTiket(ticket: List<TiketPengguna?>) {
        this.tiket.clear()
        this.tiket.addAll(ticket)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        srlTiketku.isRefreshing = true
    }

    override fun hideLoading() {
        srlTiketku.isRefreshing = false
    }

    override fun emptyPemesasnan() {
        rv_tiketku.gone()
        tvEmpty.visible()
    }

    override fun notEmptyPemesanan() {
        rv_tiketku.visible()
        tvEmpty.gone()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiketku, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = TiketkuPresenter(this)
        presenter.getTiket()
        setupRecyclerView()

        srlTiketku.onRefresh { presenter.getTiket()

        }
    }

    private fun setupRecyclerView() {
        adapter = TiketkuAdapter(tiket)
        rv_tiketku.setHasFixedSize(true)
        rv_tiketku.layoutManager = LinearLayoutManager(context)
        rv_tiketku.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.getTiket()
    }
}
