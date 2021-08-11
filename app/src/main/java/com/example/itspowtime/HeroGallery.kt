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
var genderX: ArrayList<String> = ArrayList()
var fNameX: ArrayList<String> = ArrayList()
var alignmentX: ArrayList<String> = ArrayList()
var p1X: ArrayList<Int> = ArrayList()
var p2X : ArrayList<Int> = ArrayList()
var p3X: ArrayList<Int> = ArrayList()
var p4X: ArrayList<Int> = ArrayList()
var p5X: ArrayList<Int> = ArrayList()
var p6X: ArrayList<Int> = ArrayList()
var fAX: ArrayList<String> = ArrayList()


class HeroGallery : AppCompatActivity() {

    lateinit var binding: ActivityHeroGalleryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = HeroAdapter(this, imagesX)


        // Layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = staggeredGridLayoutManager

        // Adapter to RecyclerView
        binding.recyclerView.adapter = adapter
    }
}
