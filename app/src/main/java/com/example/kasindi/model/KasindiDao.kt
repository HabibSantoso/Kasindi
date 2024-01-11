package com.example.kasindi.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface KasindiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * from tblUser WHERE uid = :uid")
    fun getUSer(uid: Int): Flow<User>

    @Query("SELECT * from tblUser ORDER BY nama ASC")
    fun getAllUser(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTrans(transaksi: Transaksi)

    @Update
    suspend fun updateTrans(transaksi: Transaksi)

    @Delete
    suspend fun deleteTrans(transaksi: Transaksi)

    @Query("SELECT * from tbltransaksi WHERE tid = :tid")
    fun getTrans(tid: Int): Flow<Transaksi>

    @Query("SELECT * from tbltransaksi ORDER BY tid ASC")
    fun getAllTrans(): Flow<List<Transaksi>>
}