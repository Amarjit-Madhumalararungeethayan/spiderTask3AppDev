package com.example.itspowtime.API

data class HeroDetails(
    val name : String,
    val slug : String,
    val powerstats : powerinfo,
    val appearance : looks,
    val biography : info,
    val work : abc,
    val connections: ppl,
    val images: imgs
    )