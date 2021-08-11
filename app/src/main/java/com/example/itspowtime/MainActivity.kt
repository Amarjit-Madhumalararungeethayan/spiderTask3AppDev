package com.example.itspowtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityOptionsCompat
import com.example.itspowtime.API.ApiHeroAll
import com.example.itspowtime.API.HeroDetails
import com.example.itspowtime.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animO = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        binding.imageView1.startAnimation(animO)
        binding.imageView2.startAnimation(animO)

        binding.heroC.animate().translationY(-1400f).setDuration(700).setStartDelay(2000)

        binding.up.animate().translationX(1400f).setDuration(750).setStartDelay(2000)
        binding.down.animate().translationX(-1400f).setDuration(750).setStartDelay(2000)

        homeScreen()
    }

    private fun homeScreen() {
        val countDown: CountDownTimer
        countDown = object : CountDownTimer(2200, 1000) {
            override fun onTick(millisecsToFinish: Long) {}
            override fun onFinish() {
                val intent = Intent(this@MainActivity, LoginPage::class.java)

                val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity)

                startActivity(intent,compat.toBundle())
            }
        }
        countDown.start()
    }
}