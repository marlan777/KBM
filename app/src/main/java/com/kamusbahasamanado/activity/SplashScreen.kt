package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.kamusbahasamanado.R

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Create Background Object
        val background = object : Thread() {
            override fun run(){
                try{
                    // Wait for 3 seconds
                    Thread.sleep(2000)

                    // Then call MainActivity
                    val mainIntent = Intent(baseContext,MainActivity::class.java)
                    startActivity(mainIntent)
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
        //Start Backgroud Object
        background.start()
    }
}
