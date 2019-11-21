package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamusbahasamanado.R
import com.kamusbahasamanado.data.DBHandler
import kotlinx.android.synthetic.main.activity_kamus_m2i.*
import android.content.Context
import android.view.inputmethod.InputMethodManager


class KamusM2I : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kamus_m2i)

        // Set database handler
        dbHandler = DBHandler(this)

        //Request focus
        btnCariByHuruf.requestFocus()

        //Initially don't display keyboard
        closeKeyboard()

        btnCariKataPersis.setOnClickListener(){
            closeKeyboard()

            if (txtKata2Cari.text.toString().equals("")) {
                Toast.makeText(this, "Kata yang dicari TIDAK BOLEH KOSONG!", Toast.LENGTH_LONG).show()
            }
            else {
                val listArtiKata = dbHandler.cariKataPersis(txtKata2Cari.text.toString())

                val adapter = ArtiKataAdapter(listArtiKata)
                val rv: RecyclerView = findViewById(R.id.rv_ArtiKata)

                rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                rv.adapter = adapter

                if (listArtiKata.size > 0) {
                    Toast.makeText(this,listArtiKata.size.toString() + " Kata:" + txtKata2Cari.text.toString() + " Ditemukan!",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"Kata:" + txtKata2Cari.text.toString() + " TIDAK Ditemukan!",Toast.LENGTH_LONG).show()
                }
            }
        }

        btnCariKataAwalan.setOnClickListener(){
            closeKeyboard()

            if (txtKata2Cari.text.isNotBlank()) {
                val listArtiKata = dbHandler.cariKataAwalan(txtKata2Cari.text.toString())

                val adapter = ArtiKataAdapter(listArtiKata)
                val rv: RecyclerView = findViewById(R.id.rv_ArtiKata)

                rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) //as RecyclerView.LayoutManager
                rv.adapter = adapter

                if (listArtiKata.size>0) {
                    Toast.makeText(this, listArtiKata.size.toString()+" Kata:"+ txtKata2Cari.text.toString() +" Ditemukan!", Toast.LENGTH_SHORT).show()
                }
                else {

                    Toast.makeText(this, "Kata:"+ txtKata2Cari.text.toString() +" TIDAK Ditemukan!", Toast.LENGTH_LONG).show()
                }
                //Toast.makeText(this, listArtiKata.size.toString()+" Kata:"+ txtKata2Cari.text.toString() +" Ditemukan!"  + txtKata2Cari.text.length.toString(), Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Kata yang dicari TIDAK BOLEH KOSONG!", Toast.LENGTH_LONG).show()
            }
        }

        btnCariKataMirip.setOnClickListener(){
            closeKeyboard()

            if (txtKata2Cari.text.isNotBlank()&& (txtKata2Cari.text.length>1) ) {
                val listArtiKata = dbHandler.cariKataMirip(txtKata2Cari.text.toString())

                val adapter = ArtiKataAdapter(listArtiKata)
                val rv: RecyclerView = findViewById(R.id.rv_ArtiKata)

                rv.layoutManager = LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false
                ) //as RecyclerView.LayoutManager
                rv.adapter = adapter

                if (listArtiKata.size > 0) {
                    Toast.makeText(
                        this,
                        listArtiKata.size.toString() + " Kata:" + txtKata2Cari.text.toString() + " Ditemukan!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Kata:" + txtKata2Cari.text.toString() + " TIDAK Ditemukan!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            else {
                Toast.makeText(this, "Suku Kata yang dicari MINIMAL 2 HURUF!", Toast.LENGTH_LONG).show()
            }
        }

        btnCariByHuruf.setOnClickListener(){
            closeKeyboard()

            // Reset if any previous searching text exitst
            txtKata2Cari.setText("")

            if (spHuruf.selectedItem.toString().equals("")) {
                Toast.makeText(this, "HURUF yang dicari TIDAK BOLEH KOSONG!", Toast.LENGTH_LONG).show()
            }
            else {
                val listArtiKata = dbHandler.cariKataAwalan(spHuruf.selectedItem.toString())

                val adapter = ArtiKataAdapter(listArtiKata)
                val rv: RecyclerView = findViewById(R.id.rv_ArtiKata)

                rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
                rv.adapter = adapter

                if (listArtiKata.size > 0) {
                    Toast.makeText(this,listArtiKata.size.toString() + " Kata berawalan:" + spHuruf.selectedItem.toString() + " Ditemukan!",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this,"Kata berawalan:" + spHuruf.selectedItem.toString() + " TIDAK Ditemukan!",Toast.LENGTH_LONG).show()
                }
            }
        }

        btnTampilkanSemua.setOnClickListener(){
            val listArtiKata = dbHandler.listAllKata()

            closeKeyboard()
            if (listArtiKata.size>0) {
                val adapter = ArtiKataAdapter(listArtiKata)
                val rv : RecyclerView = findViewById(R.id.rv_ArtiKata)

                // Reset if any previous searching text exitst
                txtKata2Cari.setText("")

                rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false) //as RecyclerView.LayoutManager
                rv.adapter = adapter

                Toast.makeText(this, listArtiKata.size.toString()+" Kata Ditemukan!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "TIDAK Ada Kata Ditemukan!", Toast.LENGTH_LONG).show()
            }
        }

        // BACK Button Listener -----------------------------------------------
        btnBack.setOnClickListener() {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
