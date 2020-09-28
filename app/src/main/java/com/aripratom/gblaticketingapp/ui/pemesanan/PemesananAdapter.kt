package com.aripratom.gblaticketingapp.ui.pemesanan

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.data.Transaksi
import com.aripratom.gblaticketingapp.ui.pemesananDetail.PemesananDetailActivity
import kotlinx.android.synthetic.main.item_pemesanan.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class PemesananAdapter(private val pemesanan: MutableList<Transaksi?>) :
    RecyclerView.Adapter<PemesananAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pemesanan, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pemesanan.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pemesanan[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(pemesanan: Transaksi?) {
            try {
                Log.d("PEMESANAN",pemesanan.toString())
                itemView.tvMatch.text = pemesanan?.match
                itemView.tvTanggal.text = pemesanan?.tanggal

                //itemView.viewPemesanan.onClick {

                  //  itemView.context.startActivity<PemesananDetailActivity>("pemesanan" to pemesanan)
                //}

            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.d("ASSSSSSSS",itemView.toString())
            }

        }
    }
}