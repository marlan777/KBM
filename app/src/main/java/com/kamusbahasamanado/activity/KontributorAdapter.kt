package com.kamusbahasamanado.activity

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.kamusbahasamanado.R
import kotlinx.android.synthetic.main.rv_kontrib_item.view.*

class KontribAdapter(private val listKontrib:ArrayList<String>) : RecyclerView.Adapter<KontribAdapter.ViewHolder>() {

    // A ViewHolder Class that Pair View Fields with XML Layout
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val fieldKontribView     = itemView.txtKontribName

        // Special Container, contain all the fields, to catch ClickListener
        val cardKata          = itemView.cardView
    }

    // A ViewHolder Function that Pair Data Fields with View Fields in ViewHolder
    override fun onBindViewHolder(holder: KontribAdapter.ViewHolder, position: Int) {
        holder.fieldKontribView.text  = listKontrib[position]

        // Catch ClickListener on Item in RecyclerView (Enclosed in card)
        holder.cardKata.setOnClickListener {

            //intentTampilkanKontrib, handle the onClickListener for 1 Item Kontributor
            val intentTampilkanKontrib = Intent(holder.cardKata.context, TampilkanKontribM2I::class.java)

            // PutExtras
            intentTampilkanKontrib.putExtra("extKontrib", listKontrib[position])

            // Call Intent
            startActivity(holder.cardKata.context, intentTampilkanKontrib,null)

            Log.v("KontribAdapter.", listKontrib[position])
        }
    }

    // A ViewHolder Function that Create the Real XML Layout View of a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontribAdapter.ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.rv_kontrib_item,parent,false)
        return ViewHolder(layoutView)
    }

    // A ViewHolder Function that Calculate the size off Data Array given to Adapter
    override fun getItemCount(): Int {
        return listKontrib.size
    }

}
