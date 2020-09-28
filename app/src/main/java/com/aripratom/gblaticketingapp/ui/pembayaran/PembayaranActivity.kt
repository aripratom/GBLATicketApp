package com.aripratom.gblaticketingapp.ui.pembayaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.Kursi
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.ui.pembayaranMenunggu.MenungguPembayaranActivity
import kotlinx.android.synthetic.main.activity_pembayaran.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class PembayaranActivity : AppCompatActivity(), PembayaranView {

    private lateinit var presenter: PembayaranPresenter
    private lateinit var transaksi: Transaksi
    private var tid: String? = null
    private var uid: String? = null
    private var kid: String? = null
    private var pid: String? = null
    private lateinit var pertandingan: Pertandingan
    private lateinit var kursi: Kursi




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        supportActionBar?.title = getString(R.string.pembayaran)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PembayaranPresenter(this)
        //presenter.setID()

        btnBayarPembayaran.onClick {

            startActivity<MenungguPembayaranActivity>("idTransaksi" to tid) }




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
