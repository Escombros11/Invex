package com.example.invex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class Splash : AppCompatActivity() {
    private var animaciontest: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animaciontest = findViewById<ImageView>(R.id.animaciontest)

        Glide.with(this).asGif().load(R.drawable.test).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(animaciontest!!)


        Handler().postDelayed({
            try {

                val intent = Intent(applicationContext, Entradas()::class.java).apply {}
                startActivity(intent)

            } catch (Ex: Exception) {
                var asr: String = Ex.message.toString()

            }
        }, 2000)

    }
}