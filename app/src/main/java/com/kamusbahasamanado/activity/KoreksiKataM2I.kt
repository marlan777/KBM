package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.kamusbahasamanado.R
import kotlinx.android.synthetic.main.activity_koreksi_kata_m2i.*

class KoreksiKataM2I : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koreksi_kata_m2i)

        Log.d("KoreksiKata", "Before getExtra")

        // Get Data AtriKata from Transfer Intent
        txtKataIn.setText(intent.extras?.getString("extKata"    ))
        txtArtiIn.setText(intent.extras?.getString("extArti"    ))
        txtContohIn.setText(intent.extras?.getString("extContoh"))

        Log.d("KoreksiKata", "After getExtra")

        // KIRIM Button Listener --------------
        btnKirimKoreksiKata.setOnClickListener(){
            if (txtKataIn.text.isNotEmpty() && txtArtiIn.text.isNotEmpty()) {
                val msgNewArtiKata = "KOREKSI:"+ txtKataIn.text + "; ARTI:" + txtArtiIn.text + "; CONTOH:" + txtContohIn.text + "; DARI:" + txtCreditIn.text

                val sendWAIntent = Intent()
                sendWAIntent.action = Intent.ACTION_SEND
                sendWAIntent.type = "text/plain"
                sendWAIntent.setPackage("com.whatsapp")
                sendWAIntent.putExtra(Intent.EXTRA_TEXT, msgNewArtiKata)
                sendWAIntent.putExtra("jid", "6285825099100" + "@s.whatsapp.net")
                startActivity(sendWAIntent)
            }
            else {
                Toast.makeText(this, "Entry Kata TIDAK LENGKAP!", Toast.LENGTH_LONG).show()
            }
        }

    }
}
