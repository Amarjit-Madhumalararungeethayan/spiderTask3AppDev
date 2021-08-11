package com.example.itspowtime.API

import retrofit2.Call
import retrofit2.http.GET

interface ApiHeroAll{

    @GET("all.json")
    fun fetchDetails() : Call<List<HeroDetails>>
}
