package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kamusbahasamanado.R
import kotlinx.android.synthetic.main.activity_tentang_kamus_m2i.*

class TentangKamusM2I : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang_kamus_m2i)

        // BACK Button Listener -----------------------------------------------
        btnBack.setOnClickListener() {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }

    }
}
