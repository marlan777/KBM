package com.kamusbahasamanado.activity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import com.kamusbahasamanado.R
import com.kamusbahasamanado.model.ArtiKata
import kotlinx.android.synthetic.main.rv_kamus_item.view.*

class ArtiKataAdapter(private val listArtiKata:ArrayList<ArtiKata>) : RecyclerView.Adapter<ArtiKataAdapter.ViewHolder>() {

    // A ViewHolder Class that Pair View Fields with XML Layout
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val fieldKataView     = itemView.txtKata
        val fieldArtiView     = itemView.txtArti
        val fieldContohView   = itemView.txtContoh

        // Special Container, contain all the fields, to catch ClickListener
        val cardKata          = itemView.cardView
    }

    // A ViewHolder Function that Pair Data Fields with View Fields in ViewHolder
    override fun onBindViewHolder(holder: ArtiKataAdapter.ViewHolder, position: Int) {
        holder.fieldKataView.text     = listArtiKata[position].Kata
        holder.fieldArtiView.text     = listArtiKata[position].Arti
        holder.fieldContohView.text   = listArtiKata[position].Contoh

        // Catch ClickListener on Item in RecyclerView (Enclosed in card)
        holder.cardKata.setOnClickListener {

            //intentTampilkanKata, handle the onClickListener for 1 Item Kata
            val intentTampilkanKata = Intent(holder.cardKata.context, TampilkanKataM2I::class.java)

            // PutExtras
            intentTampilkanKata.putExtra("extKata"  , listArtiKata[position].Kata   )
            intentTampilkanKata.putExtra("extArti"  , listArtiKata[position].Arti   )
            intentTampilkanKata.putExtra("extContoh", listArtiKata[position].Contoh )
            intentTampilkanKata.putExtra("extCredit", listArtiKata[position].Credit )
            intentTampilkanKata.putExtra("extLink1" , listArtiKata[position].Link1  )
            intentTampilkanKata.putExtra("extLink2" , listArtiKata[position].Link2  )

            // Call Intent
            startActivity(holder.cardKata.context, intentTampilkanKata,null)
        }
    }

    // A ViewHolder Function that Create the Real XML Layout View of a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtiKataAdapter.ViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.rv_kamus_item,parent,false)
        return ViewHolder(layoutView)
    }

    // A ViewHolder Function that Calculate the size off Data Array given to Adapter
    override fun getItemCount(): Int {
        return listArtiKata.size
    }

}
