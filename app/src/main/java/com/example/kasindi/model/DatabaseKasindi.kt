package com.example.kasindi.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Transaksi::class], version = 1, exportSchema = false)
abstract class DatabaseKasindi : RoomDatabase() {
}