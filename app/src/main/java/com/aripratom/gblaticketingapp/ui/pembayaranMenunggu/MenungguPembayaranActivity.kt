package com.aripratom.gblaticketingapp.ui.pembayaranMenunggu

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_menunggu_pembayaran.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MenungguPembayaranActivity : AppCompatActivity() {


    private lateinit var transaksi: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menunggu_pembayaran)

        supportActionBar?.title = getString(R.string.pembayaran)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        transaksi = intent.getStringExtra("idTransaksi")
        getIdTransaksi(transaksi)

        btnLanjutkan.onClick { startActivity<MainActivity>()
            toast(R.string.btn_pemesanan)
        }
    }

    fun getIdTransaksi(transaksi: String?){
        tvTanggalPembayaran.text = transaksi
    }
/*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
    
 */


}
