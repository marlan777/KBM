package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kamusbahasamanado.R
import kotlinx.android.synthetic.main.activity_usulkan_kata_m2i.*

class UsulkanKataM2I : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usulkan_kata_m2i)

        txtKataIn.setText(intent.extras?.getString("extKata"     ))
        txtArtiIn.setText(intent.extras?.getString("extArti"     ))
        txtContohIn.setText(intent.extras?.getString("extContoh"   ))
        txtCreditIn.setText(intent.extras?.getString("extCredit"   ))

        // KIRIM Button Listener --------------
        btnUsulKataBaru.setOnClickListener(){
            if (txtKataIn.text.isNotEmpty() && txtArtiIn.text.isNotEmpty()) {
                val msgNewArtiKata = "KATA:"+ txtKataIn.text + "; ARTI:" + txtArtiIn.text + "; CONTOH:" + txtContohIn.text + "; DARI:" + txtCreditIn.text

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
