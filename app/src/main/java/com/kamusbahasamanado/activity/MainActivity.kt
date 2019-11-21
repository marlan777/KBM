package com.kamusbahasamanado.activity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kamusbahasamanado.model.*
import kotlinx.android.synthetic.main.activity_main.*
import com.kamusbahasamanado.R
import com.kamusbahasamanado.data.DBHandler
import java.io.IOException


class MainActivity : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For the first time, check if database is already there
        // Copy it from Assets if it's not exist yet
        // NOTE : Later, use SharedPreference to check just once
        val dbFileExist : Boolean = this.getDatabasePath(DATABASE_NAME).exists()
        dbHandler = DBHandler(this)

        if (!dbFileExist){
            dbHandler.installDatabaseFromAssets()

            // Log database writing status, and return
            //Log.v("MainActivity", "dbFile NOT EXIST! CREATED")
        }
        else {
            // Log database writing status, and return
            //Log.v("MainActivity", "dbFile ALREADY EXIST! NOT CREATED")
        }

        btnKamusM2I.setOnClickListener() {
            val intentKamusM2I = Intent(this, KamusM2I::class.java)
            startActivity(intentKamusM2I)
        }

        btnCRUDKamusManado.setOnClickListener(){
            val intentCRUDKamusM2I = Intent(this, CRUDKamusM2I::class.java)
            startActivity(intentCRUDKamusM2I)
        }

        btnUsulKataM2I.setOnClickListener() {
            // Check if WhatsApp Exist. Act accordingly :)
            val pm = this.packageManager
            try {
                val testWA = pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES).toString()
                Toast.makeText(this, testWA + " Exist. Continue ...", Toast.LENGTH_SHORT).show()

                // WhatsApp Installed, Call Intent For UsulkanKata via WhatsApp
                val intentUsulkanKataM2I = Intent(this, UsulkanKataM2I::class.java)
                startActivity(intentUsulkanKataM2I)
            }
            catch (e: IOException){
                Toast.makeText(this, "WhatsApp NOT Installed" , Toast.LENGTH_LONG).show()
            }
        }

        btnTentangKamus.setOnClickListener(){
            val intentTentangKamusM2I = Intent(this, TentangKamusM2I::class.java)
            startActivity(intentTentangKamusM2I)
        }

        btnKontributor.setOnClickListener(){
            val intentKontributorM2I = Intent(this, KontributorM2I::class.java)
            startActivity(intentKontributorM2I)
        }

        btnSelesai.setOnClickListener(){
            val alertExit = AlertDialog.Builder(this)
            alertExit.setTitle(R.string.app_name)
            alertExit.setMessage("Ingin keluar dari Aplikasi ini?")
            alertExit.setCancelable(false)
            alertExit.setPositiveButton("YA") {_, _->
                Toast.makeText(this, "Keluar, Aplikasi Ditutup!", Toast.LENGTH_LONG).show()
                finishAffinity()
                System.exit(0)
            }
            alertExit.setNegativeButton("TIDAK") {_, _->
                Toast.makeText(this, "Batal Keluar dari Aplikasi!", Toast.LENGTH_LONG).show()
            }
            alertExit.setNeutralButton("BATAL") {_,_->
                Toast.makeText(this, "Pilihan Dibatalkan!", Toast.LENGTH_LONG).show()
            }
            alertExit.show()
        }
    }
}
