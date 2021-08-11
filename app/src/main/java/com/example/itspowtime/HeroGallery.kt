package com.example.itspowtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.itspowtime.API.ApiHeroAll
import com.example.itspowtime.databinding.ActivityHeroGalleryBinding
import retrofit2.Call
import com.example.itspowtime.API.HeroDetails
import com.example.itspowtime.Adapter.HeroAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

var imagesX: ArrayList<String> = ArrayList()

class HeroGallery : AppCompatActivity() {

    lateinit var binding: ActivityHeroGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HeroAdapter(this, imagesX)

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
                }

                Log.d("Amarjit", "${imagesX}")

                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<HeroDetails>>, t: Throwable) {
                Log.d("Amarjit", "onFailure")
                t.message?.let { Log.d("server upload abc", it) }
            }

        })
        // Layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = staggeredGridLayoutManager

        // Adapter to RecyclerView
        binding.recyclerView.adapter = adapter
    }
}
