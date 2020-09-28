package com.aripratom.gblaticketingapp.ui.kursi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.data.Kursi
import com.aripratom.gblaticketingapp.data.Pertandingan
import com.aripratom.gblaticketingapp.data.TiketPengguna
import com.aripratom.gblaticketingapp.ui.order.OrderActivity
import com.aripratom.gblaticketingapp.ui.pertandingan.PertandinganAdapter
import kotlinx.android.synthetic.main.activity_kursi.view.*
import kotlinx.android.synthetic.main.item_kursi.view.*
import kotlinx.android.synthetic.main.item_pertandingan.view.*
import kotlinx.android.synthetic.main.item_pertandingan.view.viewPertandingan
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class KursiAdapter (private val kursi : MutableList<Kursi?>, private val booked: MutableList<TiketPengguna?>, private val idPertandingan : Int) : RecyclerView.Adapter<KursiAdapter.ViewHolder>(){

    private var onItemClickCallback: KursiAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: KursiAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_kursi, parent, false))
    }

    override fun getItemCount(): Int {
        return kursi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(kursi[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindItem(kursi: Kursi?){
            itemView.no_kursi.text = kursi?.idKursi
            //itemView.no_kursi.onClick { itemView.context.startActivity<OrderActivity>("kursi" to kursi) }
            itemView.viewKursi.setOnClickListener {
                onItemClickCallback?.onItemClickCallback(kursi!!)
            }
           // Log.d("AAAAAAAAAAAAAAA", booked.toString())
            booked.forEach {
                if (it!!.idPertandingan == idPertandingan ) {
                    //Log.d("AAAAAAAAAAAAAAA", it!!.idKursi.toString())
                    if (kursi!!.idKursi == it.idKursi) {
                        itemView.viewKursi.isEnabled = false
                        itemView.no_kursi.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
                        itemView.no_kursi.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))

                    } //else {
                        //itemView.viewKursi.isEnabled = true
                        //itemView.no_kursi.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))
                        //itemView.no_kursi.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))

                    }
                //}
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClickCallback(data: Kursi)
    }
}