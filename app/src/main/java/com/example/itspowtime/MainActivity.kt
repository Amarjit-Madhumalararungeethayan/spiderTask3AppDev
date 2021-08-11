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

var nameS: ArrayList<String> = ArrayList()
var genderS: ArrayList<String> = ArrayList()
var fNameS: ArrayList<String> = ArrayList()
var alignmentS: ArrayList<String> = ArrayList()
var p1S: ArrayList<Int> = ArrayList()
var p2S : ArrayList<Int> = ArrayList()
var p3S: ArrayList<Int> = ArrayList()
var p4S: ArrayList<Int> = ArrayList()
var p5S: ArrayList<Int> = ArrayList()
var p6S: ArrayList<Int> = ArrayList()
var fAS: ArrayList<String> = ArrayList()

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

        //setting up retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://akabab.github.io/superhero-api/api/")  //must end with fwd slash - else you'll get an error and will struggle for 30 mins 😂
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiHeroAll::class.java)

        api.fetchDetails().enqueue(object : Callback<List<HeroDetails>> {
            override fun onResponse(call: Call<List<HeroDetails>>, response: Response<List<HeroDetails>>
            ) {
                Log.d("Amarjit", "${response.body()!![4].images.md}")

                for( i in 0..550){
                    nameS.add(response.body()!![i].name)
                    genderS.add(response.body()!![i].appearance.gender)
                    fNameS.add(response.body()!![i].biography.fullName)
                    alignmentS.add(response.body()!![i].biography.alignment)
                    p1S.add(response.body()!![i].powerstats.intelligence)
                    p2S.add(response.body()!![i].powerstats.strength)
                    p3S.add(response.body()!![i].powerstats.speed)
                    p4S.add(response.body()!![i].powerstats.durability)
                    p5S.add(response.body()!![i].powerstats.power)
                    p6S.add(response.body()!![i].powerstats.combat)
                    fAS.add(response.body()!![i].biography.firstAppearance)

                }
            }

            override fun onFailure(call: Call<List<HeroDetails>>, t: Throwable) {
                Log.d("Amarjit", "onFailure")
                t.message?.let { Log.d("server upload abc", it) }
            }

        })

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