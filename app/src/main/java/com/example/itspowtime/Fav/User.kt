package com.example.itspowtime.Fav

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//rep table in database
@Entity(tableName = "favS1")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo(name = "name_of_superhero")
    val name : String
)
