package com.aripratom.gblaticketingapp.ui.pertandingan

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.ui.kursi.KursiActivity
import com.aripratom.gblaticketingapp.util.DateUtil.locale
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pertandingan.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class PertandinganAdapter(private val pertandingan: MutableList<Pertandingan?>) :
    RecyclerView.Adapter<PertandinganAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pertandingan, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pertandingan.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(pertandingan[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(pertandingan: Pertandingan?) {
            try {
                Glide.with(itemView.context).load(pertandingan?.logoHome)
                    .placeholder(R.mipmap.ic_launcher_round).into(itemView.imgHomeTeam)

                Glide.with(itemView.context).load(pertandingan?.logoAway)
                    .placeholder(R.mipmap.ic_launcher_round).into(itemView.imgAwayTeam)

                itemView.tvDay.text = pertandingan?.hari
                itemView.tvTime.text = pertandingan?.jamPertandingan
                itemView.tvDate.text = pertandingan?.tanggalFull
                itemView.tvHomeTeam.text = pertandingan?.teamHome
                itemView.tvAwayTeam.text = pertandingan?.teamAway
                itemView.tvStatus.text = pertandingan?.status


                //itemView.tvDate.text = dateFormat(pertandingan?.tanggal, "dd/MM/yyyy")
                isExpire(pertandingan?.tanggal!!, pertandingan.status!!)
                //isUnAvailable(pertandingan.status!!)

                /*
                if (pertandingan?.idPertandingan == tiketPengguna?.idPertandingan){
                    itemView.viewPertandingan.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.silver))
                    itemView.viewPertandingan.isEnabled = false
                    itemView.tvStatus.setText("Tidak Tersedia")
                    itemView.tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))

                }*/

                itemView.viewPertandingan.onClick { itemView.context.startActivity<KursiActivity>("pertandingan" to pertandingan) }

                Log.d("BRRRRRRR",itemView.toString())

            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.d("ASSSSSSSS",itemView.toString())
            }

        }
        private fun isExpire(date: String, status: String){
            val parseDate: Date = SimpleDateFormat("dd/MM/yyyy", locale).parse(date)
            Log.d("DDDDDDDDDD",Date().toString())


            //if (Date().after(parseDate) || status == "Belum Tersedia") {
            if (status == "Belum Tersedia") {
                itemView.viewPertandingan.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.silver))
                itemView.viewPertandingan.isEnabled = false
                itemView.tvStatus.setText("Tidak Tersedia")
                itemView.tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))

              //  Log.d("DDDDDDDDDDDD",Date().toString())

            } else{
                itemView.viewPertandingan.isEnabled = true
                itemView.viewPertandingan.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
                itemView.tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
            }
        }
/*
        private fun isUnAvailable(status: String){
            if (status == "Belum Tersedia"){
                itemView.viewPertandingan.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.silver))
                itemView.viewPertandingan.isEnabled = false
                itemView.tvStatus.setText("Belum Tersedia")
                itemView.tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.red))
            }else{
                itemView.viewPertandingan.isEnabled = true
                itemView.viewPertandingan.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
                itemView.tvStatus.setTextColor(ContextCompat.getColor(itemView.context, R.color.common_google_signin_btn_text_light_default))
            }

        }
*/
    }


}