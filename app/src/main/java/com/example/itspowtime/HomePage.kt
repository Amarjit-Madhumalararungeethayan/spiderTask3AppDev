package com.example.itspowtime

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.itspowtime.API.ApiHeroAll
import com.example.itspowtime.API.HeroDetails
import com.example.itspowtime.Fav.UserDatabase
import com.example.itspowtime.databinding.ActivityHomePageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

var favNames : ArrayList<String> = ArrayList()
var favImages : ArrayList<String> = ArrayList()

class HomePage : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomePageBinding

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHomePage.toolbar)



        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home_page)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_h, R.id.nav_v, R.id.nav_male, R.id.nav_female, R.id.nav_favs
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

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
                Log.d("Amarjit", "${response.body()!![5].images.md}")
                Log.d("Amarjit", "${response.body()!![6].images.md}")

                for( i in 0..550){
                    imagesX.add(response.body()!![i].images.lg)
                    genderX.add(response.body()!![i].appearance.gender)
                    fNameX.add(response.body()!![i].biography.fullName)
                    alignmentX.add(response.body()!![i].biography.alignment)
                    p1X.add(response.body()!![i].powerstats.intelligence)
                    p2X.add(response.body()!![i].powerstats.strength)
                    p3X.add(response.body()!![i].powerstats.speed)
                    p4X.add(response.body()!![i].powerstats.durability)
                    p5X.add(response.body()!![i].powerstats.power)
                    p6X.add(response.body()!![i].powerstats.combat)
                    fAX.add(response.body()!![i].biography.firstAppearance)

                }

            }

            override fun onFailure(call: Call<List<HeroDetails>>, t: Throwable) {
                Log.d("Amarjit", "onFailure")
                t.message?.let { Log.d("server upload abc", it) }
            }

        })


        val myRoomDatabase = Room.databaseBuilder(this,UserDatabase::class.java,"favS1").allowMainThreadQueries().build()

        Log.d("Size of Database", "${myRoomDatabase.userDao().getRowCount()}")

        for(i in 1..myRoomDatabase.userDao().getRowCount()){
            favNames.add(myRoomDatabase.userDao().getName(i))
        }

        Log.d("CHECKER","${favNames}")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_page, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_page)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}