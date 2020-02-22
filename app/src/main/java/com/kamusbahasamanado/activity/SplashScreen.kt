package com.kamusbahasamanado.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import com.kamusbahasamanado.R
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected) {
            // Invalidate Picasso image cache
            // Loading Image from URL. If none detected, use default and errorplaceholder
            Picasso.get().invalidate("http://www.pamuru.net/KamusManadoIklan.jpg")

            Picasso.get().load("http://www.pamuru.net/KamusManadoIklan.jpg")
                .memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .error(R.drawable.img_manado_2)
                .centerInside()
                .fit()
                .into(imgIklanApp)
        }

        // Create Background Object, to start MainActivity
        val background = object : Thread() {
            override fun run(){
                try{
                    // Wait for 3 seconds
                    sleep(5000)

                    // Then call MainActivity
                    val mainIntent = Intent(baseContext,MainActivity::class.java)
                    startActivity(mainIntent)

                    // Finish this splash screen
                    //finish()
                } catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
        //Start Backgroud Object
        background.start()
    }
}
