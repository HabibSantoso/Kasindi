package com.example.kasindi.repositori

import com.example.kasindi.model.Transaksi
import com.example.kasindi.model.User
import kotlinx.coroutines.flow.Flow

interface RepositoriKasindi {
    fun getAllUserStream(): Flow<List<User>>

    fun getUserStraem(uid: Int): Flow<User>

    suspend fun insertU(user: User)

    suspend fun deleteU(user: User)

    suspend fun updateU(user: User)

    fun getAllTransStream(): Flow<List<Transaksi>>

    fun getTransStream(tid: Int): Flow<Transaksi>

    suspend fun insertT(transaksi: Transaksi)

    suspend fun deleteT(transaksi: Transaksi)

    suspend fun updateT(transaksi: Transaksi)
}