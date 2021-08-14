package com.example.itspowtime.Fav

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//contains methods used for accessing the database

@Dao
interface UserDao  {

    @Insert()
    fun addUser(user: User)

    @Query("SELECT * FROM favS1 WHERE id LIKE :key")
    fun findById(key: Long) : User?

    @Query("SELECT COUNT(id) FROM favS1")
    fun getRowCount() : Int

    @Query("SELECT name_of_superhero FROM favs1 WHERE id LIKE :key")
    fun getName(key : Int) : String

    @Query("DELETE FROM favS1")
    fun clear()

}


