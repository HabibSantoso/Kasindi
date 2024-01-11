package com.example.kasindi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblUser")
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid : Int = 0,
    val nama : String,
    val email : String,
    val isadmin : Boolean = false
)

@Entity(tableName = "tbltransaksi")
data class Transaksi(
    @PrimaryKey(autoGenerate = true)
    val tid : Int = 0,
    val keterangan : String,
    val tipe : String,
    val nominal : Int
)

