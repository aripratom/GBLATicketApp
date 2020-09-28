package com.aripratom.gblaticketingapp.ui.pemesanan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.gblaticketingapp.Application
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.Transaksi
import kotlinx.android.synthetic.main.item_pemesanan.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PemesananAdapter2(
    private val transaksi: MutableList<Transaksi?>,
    private val booked: MutableList<TiketPengguna?>
) : RecyclerView.Adapter<PemesananAdapter2.ViewHolder>() {

    private var onItemClickCallback: PemesananAdapter2.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: PemesananAdapter2.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pemesanan,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return transaksi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(transaksi[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(transaksi: Transaksi?) {
            itemView.tvMatch.text = transaksi?.match
            itemView.tvNoKursiPemesanan.text = transaksi?.idKursi
            itemView.tvTanggal.text = transaksi?.tanggalFull
            itemView.viewPemesanan.setOnClickListener {
                onItemClickCallback?.onItemClickCallback(transaksi!!)
            }

            booked.forEach {
                if (it!!.id == transaksi?.id) {
                    if (transaksi!!.tanggal == it.tanggal) {
                        itemView.viewPemesanan.isEnabled = false
                        itemView.viewPemesanan.setBackgroundColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.silver
                            )
                        )
                        itemView.tvLihatDetail.setText("")
                        itemView.tvStatus2.setText("Anda sudah memiliki tiket pada pertandingan ini")
                        itemView.tvStatus2.setTextColor(
                            ContextCompat.getColor(
                                itemView.context,
                                R.color.red
                            )
                        )
                        // itemView.btnBayarPemesanan.onClick { Toast.makeText(,"",Toast.LENGTH_SHORT).show }
                    }

                } else if (it.id != transaksi?.id) {
                    if (transaksi!!.tanggal == it.tanggal) {
                        if (transaksi!!.idKursi == it.idKursi) {
                            itemView.viewPemesanan.isEnabled = false
                            itemView.viewPemesanan.setBackgroundColor(
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color.silver
                                )
                            )
                            itemView.tvLihatDetail.setText("")
                            itemView.tvStatus2.setText("Maaf, kursi ini tidak tersedia, silahkan pilih  kursi yang lain")
                            itemView.tvStatus2.setTextColor(
                                ContextCompat.getColor(
                                    itemView.context,
                                    R.color.red
                                )
                            )
                        }
                    }
                }

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClickCallback(data: Transaksi)
    }
}