package com.example.kasindi.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Transaksi::class], version = 1, exportSchema = false)
abstract class DatabaseKasindi : RoomDatabase() {
    abstract fun kasindiDao() : KasindiDao

    companion object{
        private  var Instance: DatabaseKasindi? = null

        fun getDatabase(context: Context): DatabaseKasindi{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabaseKasindi::class.java,
                    "Kasindi_Database").build().also { Instance = it }
            })
        }
    }
}