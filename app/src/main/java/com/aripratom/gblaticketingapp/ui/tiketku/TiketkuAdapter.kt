package com.aripratom.gblaticketingapp.ui.tiketku

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.util.FirebaseUtil.getTiketRFIDdata
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_tiket_ku_detail.view.*
import kotlinx.android.synthetic.main.item_tiketku.view.*
import kotlinx.android.synthetic.main.item_tiketku.view.imgAwayTeamTiket
import kotlinx.android.synthetic.main.item_tiketku.view.imgHomeTeamTiket
import kotlinx.android.synthetic.main.item_tiketku.view.tvAwayTeamTiket
import kotlinx.android.synthetic.main.item_tiketku.view.tvHomeTeamTiket
import kotlinx.android.synthetic.main.item_tiketku.view.viewTiket
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class TiketkuAdapter(private val tiketKu: MutableList<TiketPengguna?>) :
    RecyclerView.Adapter<TiketkuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tiketku, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tiketKu.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(tiketKu[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(tiket: TiketPengguna?) {
            try {



                Log.d("TIKETKU", tiket.toString())
                Glide.with(itemView.context).load(tiket?.logoHome)
                    .placeholder(R.mipmap.ic_launcher_round).into(itemView.imgHomeTeamTiket)

                Glide.with(itemView.context).load(tiket?.logoAway)
                    .placeholder(R.mipmap.ic_launcher_round).into(itemView.imgAwayTeamTiket)

                itemView.tvHomeTeamTiket.text = tiket?.teamHome
                itemView.tvAwayTeamTiket.text = tiket?.teamAway



                itemView.viewTiket.onClick { itemView.context.startActivity<TiketkuDetailActivity>("tiketku" to tiket) }

                getTiketRFIDdata(
                    itemView.context, tiket?.rfid, tiket?.tanggal, itemView.tvStatusTiket
                )

            } catch (ex: Exception) {
                ex.printStackTrace()
                Log.d("ASSSSSSSS", itemView.toString())
            }

        }
    }
}