package com.aripratom.gblaticketingapp.ui.pemesananDetail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.ui.pemesananKonfirmasi.PemesananKonfirmasiActivity
import com.aripratom.gblaticketingapp.ui.tiketku.TiketkuAdapter
import kotlinx.android.synthetic.main.activity_pemesanan_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class PemesananDetailActivity : AppCompatActivity(), PemesananDetailView {
    private lateinit var pemesanan: Transaksi
    private lateinit var transaksi: String
    private lateinit var adapter: TiketkuAdapter
    private lateinit var presenter: PemesananDetailPresenter
    private val ticket: MutableList<TiketPengguna?> = mutableListOf()
    private var tiketPengguna: TiketPengguna? = null
    //private val idPertandingan = null
    private lateinit var listTiketPengguna: List<TiketPengguna?>

    override fun showTiket(tiket: List<TiketPengguna?>) {
        listTiketPengguna = tiket

        val idPertandingan = listTiketPengguna.filter {
            pemesanan.idPertandingan == it!!.idPertandingan

        }

        btnKonfirmasiPembayaran.onClick {

            if (idPertandingan.size > 0){
                toast("MAAF, ANDA HANYA DAPAT MEMBELI SATU TIKET")
                Log.d("LIIIIIIIIIIIII", listTiketPengguna.size.toString())
            }else{
                startActivity<PemesananKonfirmasiActivity>("pemesanan" to pemesanan)
            }
        }

    }

    override fun showData(tiket: List<TiketPengguna?>) {
        val idKursi = tiket.filter {
            pemesanan.idKursi == it!!.idKursi
        }
        btnKonfirmasiPembayaran.onClick {

            if (idKursi.size > 0){
                toast("MAAF, KURSI INI TIDAK TERSEDIA")
                Log.d("KURRRRRRRR", idKursi.size.toString())
            }else{
                startActivity<PemesananKonfirmasiActivity>("pemesanan" to pemesanan)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pemesanan_detail)

        supportActionBar?.title = getString(R.string.pemesanan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PemesananDetailPresenter(this)

        pemesanan = intent.getParcelableExtra("pemesanan")
        getPemesanan(pemesanan)
        presenter.getTiket()
        presenter.getTiketIdPertandingan()

    }

    private fun getPemesanan(pemesanan: Transaksi?) {

        tvKodePembayaran.text = pemesanan?.idTransaksi
        tvPertandinganPemesananDetail.text = pemesanan?.match
        tvTanggalPembayaran.text = pemesanan?.tanggalFull
        noKursiPembayaran.text = pemesanan?.idKursi

    }

    override fun onResume() {
        super.onResume()
        presenter.getTiket()
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
