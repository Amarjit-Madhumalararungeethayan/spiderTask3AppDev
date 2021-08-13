package com.example.itspowtime.Fav

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//contains database holder and serves as the main access point

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    public abstract fun userDao() : UserDao

    /**
        private var INSTANCE: UserDatabase? = null
        fun getInstance(context : Context) : UserDatabase{
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "favs"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
        }
        **/
    }