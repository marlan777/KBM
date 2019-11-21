package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamusbahasamanado.R
import com.kamusbahasamanado.data.DBHandler
import kotlinx.android.synthetic.main.activity_kontributor_m2i.*

class KontributorM2I : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kontributor_m2i)

        dbHandler = DBHandler(this)

        val listKontributor = dbHandler.listAllKontrib()

        if (listKontributor.size>0) {
            val adapter = KontribAdapter(listKontributor)
            val rv : RecyclerView = findViewById(R.id.rv_Kontributor)

            rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false) //as RecyclerView.LayoutManager
            rv.adapter = adapter

            Toast.makeText(this, listKontributor.size.toString()+" Kontributor Ditemukan!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "TIDAK Ada Kontributor Ditemukan!", Toast.LENGTH_LONG).show()
        }

        // BACK Button Listener -----------------------------------------------
        btnBack.setOnClickListener() {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }

    }
}
