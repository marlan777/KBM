package com.kamusbahasamanado.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamusbahasamanado.R
import com.kamusbahasamanado.data.DBHandler

class TampilkanKontribM2I : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilkan_kontrib_m2i)

        dbHandler = DBHandler(this)

        val txtKontribIn : String? = intent.extras?.getString("extKontrib")

        Log.v("TampilkanKontribM2I", txtKontribIn)

        val listArtiKata = dbHandler.cariKataByKontrib(txtKontribIn)

        val adapter = ArtiKataAdapter(listArtiKata)
        val rv: RecyclerView = findViewById(R.id.rv_ArtiKata)

        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        rv.adapter = adapter

        if (listArtiKata.size > 0) {
            Toast.makeText(this,listArtiKata.size.toString() + " Kata usulan dari " + txtKontribIn + " Ditemukan!",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this,"Usulan Kata dari " + txtKontribIn + " TIDAK Ditemukan!",Toast.LENGTH_LONG).show()
        }

    }
}
