package com.aripratom.gblaticketingapp.ui.pemesanan


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.ui.pemesananDetail.PemesananDetailActivity
import com.aripratom.gblaticketingapp.util.gone
import com.aripratom.gblaticketingapp.util.visible
import kotlinx.android.synthetic.main.fragment_pemesanan.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class PemesananFragment : Fragment(), PemesananView {

    private lateinit var presenter: PemesananPresenter
    private lateinit var adapter: PemesananAdapter2
    private val transaksi: MutableList<Transaksi?> = mutableListOf()
    private val booked = mutableListOf<TiketPengguna?>()
    private lateinit var listTiketPengguna: List<TiketPengguna?>
    private lateinit var transactions: Transaksi

    override fun showPemesanan(transaction: List<Transaksi?>) {
        this.transaksi.clear()
        this.transaksi.addAll(transaction)
        adapter.notifyDataSetChanged()

        /*
        val tanggal = transaction.filter {
            it!!.tanggal ==
        }

        btnBayarPemesanan.onClick {

            if (tanggal.size > 0){
                toast("MAAF ANDA HANYA DAPAT MEMBELI SATU TIKET")
                Log.d("TAAAAAAAAA", tanggal.size.toString())
            }else {
                startActivity<PemesananDetailActivity>("pemesanan" to transactions)
            }
        }

         */

    }

    override fun showData(ticket: List<TiketPengguna?>) {
        booked.addAll(ticket)

    }

    override fun showLoading() {
        srlPemesansn.isRefreshing = true
    }

    override fun hideLoading() {
        srlPemesansn.isRefreshing = false
    }

    override fun emptyPemesasnan() {
        rv_pemesanan.gone()
        tvEmptyPemesanan.visible()
    }

    override fun notEmptyPemesanan() {
        rv_pemesanan.visible()
        tvEmptyPemesanan.gone()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pemesanan, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = PemesananPresenter(this)
        presenter.getPemesanan()
        presenter.getTicketData()
        setupRecyclerView()

        srlPemesansn.onRefresh {
            presenter.getPemesanan()
            presenter.getTicketData()
        }
    }

    private fun setupRecyclerView() {
        adapter = PemesananAdapter2(transaksi, booked)
        rv_pemesanan.setHasFixedSize(true)
        rv_pemesanan.layoutManager = LinearLayoutManager(context)
        rv_pemesanan.adapter = adapter


        adapter.setOnItemClickCallback(object : PemesananAdapter2.OnItemClickCallback {
            override fun onItemClickCallback(data: Transaksi) {
                //tvTanggal.text = data.tanggal
                startActivity<PemesananDetailActivity>("pemesanan" to data)
            }
        })

    }

    override fun onResume() {
        super.onResume()
        presenter.getPemesanan()
        presenter.getTicketData()
        setupRecyclerView()
    }


}
