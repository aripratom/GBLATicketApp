package com.aripratom.gblaticketingapp.ui.pemesananKonfirmasi

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.TiketRFID
import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.ui.main.MainActivity
import com.aripratom.gblaticketingapp.ui.pemesanan.PemesananAdapter
import com.aripratom.gblaticketingapp.util.ValidateUtil
import com.aripratom.gblaticketingapp.util.ValidateUtil.emailValidate
import com.aripratom.gblaticketingapp.util.ValidateUtil.emailValidateTiket
import com.aripratom.gblaticketingapp.util.ValidateUtil.etToString
import com.aripratom.gblaticketingapp.util.ValidateUtil.kodePembayaranValidate
import kotlinx.android.synthetic.main.activity_pemesanan_konfirmasi.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class PemesananKonfirmasiActivity : AppCompatActivity(), PemesananKonfirmasiView {

    private lateinit var presenter: PemesananKonfirmasiPresenter
    private lateinit var adapter: PemesananAdapter
    private lateinit var idTransaksi: String

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showPemesanan(ticket: List<Transaksi?>) {
        adapter.notifyDataSetChanged()
    }

    override fun updateSuccess() {
        toast("Tiket berhasil dibeli! Silahkan cek tiket Anda pada halaman Tiket Saya!")
        finish()
    }

    override fun updateFailed() {
        toast("Gagal untuk membeli tiket!")
    }

    override fun eventNotFound() {
        finish()
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
        setContentView(R.layout.activity_pemesanan_konfirmasi)

        supportActionBar?.title =
            getString(com.aripratom.gblaticketingapp.R.string.konfirmasi_pemesanan)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = PemesananKonfirmasiPresenter(this)

        val pemesanan: Transaksi? = intent.getParcelableExtra("pemesanan")

        idTransaksi = etToString(etIdTransaksi)

        btnKonfirmasi.onClick {

            viewDialog(R.string.dialog_konfirmasi) {
                presenter.getPemesanan(etToString(etIdTransaksi))

                val tiketPengguna = TiketPengguna(
                    id = pemesanan?.id,
                    idTransaksi = pemesanan?.idTransaksi,
                    idKursi = pemesanan?.idKursi,
                    idPertandingan = pemesanan?.idPertandingan,
                    match = pemesanan?.match,
                    tanggal = pemesanan?.tanggal,
                    tanggalFull = pemesanan?.tanggalFull,
                    jamPertandingan = pemesanan?.jamPertandingan,
                    hargaTiket = pemesanan?.hargaTiket,
                    rfid = pemesanan?.rfid,
                    email = pemesanan?.email,
                    slotKursi = "1",
                    logoHome = pemesanan?.logoHome,
                    logoAway = pemesanan?.logoAway,
                    teamHome = pemesanan?.teamHome,
                    teamAway = pemesanan?.teamAway,
                    hari = pemesanan?.hari
                )

                val tiketRFID = TiketRFID(
                    rfid = pemesanan?.rfid,
                    slotKursi = "1",
                    tanggal = pemesanan?.tanggal
                )

                if (etToString(etIdTransaksi) == pemesanan?.idTransaksi && etToString(
                        etEmilKonfirmasi
                    ) == pemesanan.email
                ) {
                    konfirmasiPemesanan(tiketPengguna)
                    presenter.konfirmasiTiket(tiketRFID, pemesanan.idTransaksi)

                    startActivity<MainActivity>()

                    presenter.deleteTransaksi(pemesanan.idTransaksi)
                    //btnKonfirmasiPembayaran.isClickable = false
                    //btnKonfirmasiPembayaran.backgroundResource = R.color.silver

                    //tvBelumBayar.setText("LUNAS")
                    //tvBelumBayar.setTextColor(ContextCompat.getColor(applicationContext,R.color.green))

                } else {
                    if (etToString(etIdTransaksi) != pemesanan?.idTransaksi) {
                        kodePembayaranValidate(
                            etIdTransaksi,
                            pemesanan?.idTransaksi.toString(),
                            getString(R.string.idTransaksi_validate_tiket)
                        ) {
                            //(etToString(etIdTransaksi) != pemesanan?.idTransaksi)
                        }
                    }
                    if (etToString(etEmilKonfirmasi) != pemesanan?.email) {
                        emailValidate(etEmilKonfirmasi, getString(R.string.email_validate_tiket)) {
                            emailValidateTiket(
                                etEmilKonfirmasi,
                                pemesanan?.email.toString(),
                                getString(R.string.email_validate_tiket)
                            ) {

                            }
                        }
                    }
                    updateFailed()

                    //btnKonfirmasiPembayaran.isClickable = true
                    //btnKonfirmasiPembayaran.backgroundResource = R.color.colorAccent
                    //tvBelumBayar.setText("BELUM BAYAR")
                    //tvBelumBayar.setTextColor(ContextCompat.getColor(applicationContext,R.color.red))
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun konfirmasiPemesanan(tiketPengguna: TiketPengguna) {
        ValidateUtil.etValidate(
            etEmilKonfirmasi,
            getString(com.aripratom.gblaticketingapp.R.string.member_validate)
        ) {
            ValidateUtil.etValidate(
                etIdTransaksi,
                getString(com.aripratom.gblaticketingapp.R.string.idTransaksi_validate)
            ) {
                presenter.konfirmasiPemesanan(
                    tiketPengguna
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
