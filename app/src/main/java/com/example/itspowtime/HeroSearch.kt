package com.example.itspowtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.example.itspowtime.databinding.ActivityHeroSearchBinding

class HeroSearch : AppCompatActivity() {

    lateinit var binding: ActivityHeroSearchBinding

    override fun onBackPressed() {
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.load.isVisible = false

        var userInput = binding.l2.text

        var posSearch = 0

        binding.l4.setOnClickListener(){

            binding.load.isVisible = true

            for(i in 0..550){
                if( nameS[i].contains(userInput.toString(), ignoreCase = true) ){
                    posSearch = i
                    Log.d("DELTA HAHAHA", "position is $i")

                    val intent = Intent(this, HeroDetails::class.java)
                    intent.putExtra("Position", posSearch)
                    startActivity(intent)
                }
            }
        }
    }
}