package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kamusbahasamanado.R
import kotlinx.android.synthetic.main.activity_tampilkan_kata.*

class TampilkanKataM2I : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tampilkan_kata)

        // Get Data AtriKata from Transfer Intent
        txtKataIn.text      = intent.extras?.getString("extKata"     )
        txtArtiIn.text      = intent.extras?.getString("extArti"     )
        txtContohIn.text    = intent.extras?.getString("extContoh"   )
        txtCreditIn.text    = intent.extras?.getString("extCredit"   )
        txtLink1In.text     = intent.extras?.getString("extLink1"    )
        txtLink2In.text     = intent.extras?.getString("extLink2"    )

        btnKoreksi.setOnClickListener(){
            val intentKoreksiKata = Intent(this, KoreksiKataM2I::class.java)

            // PutExtras
            intentKoreksiKata.putExtra("extKata"  , txtKataIn.text.toString())
            intentKoreksiKata.putExtra("extArti"  , txtArtiIn.text.toString())
            intentKoreksiKata.putExtra("extContoh", txtContohIn.text.toString())

            // Start Activity
            startActivity(intentKoreksiKata)
        }

        // BACK Button Listener ---------------
        btnBack.setOnClickListener() {
            val intentMain = Intent(this, KamusM2I::class.java)
            startActivity(intentMain)
        }
    }
}
