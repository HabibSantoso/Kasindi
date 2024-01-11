package com.example.kasindi.repositori

import com.example.kasindi.model.KasindiDao
import com.example.kasindi.model.Transaksi
import com.example.kasindi.model.User
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriKasindi(private val kasindiDao: KasindiDao): RepositoriKasindi {
    override fun getAllUserStream(): Flow<List<User>> {
        return kasindiDao.getAllUser()
    }

    override fun getUserStraem(uid: Int): Flow<User> {
        return kasindiDao.getUSer(uid)
    }

    override suspend fun insertU(user: User) {
        kasindiDao.insertUser(user)
    }

    override suspend fun deleteU(user: User) {
        kasindiDao.deleteUser(user)
    }

    override suspend fun updateU(user: User) {
        kasindiDao.updateUser(user)
    }

    override fun getAllTransStream(): Flow<List<Transaksi>> {
        return kasindiDao.getAllTrans()
    }

    override fun getTransStream(tid: Int): Flow<Transaksi> {
        return  kasindiDao.getTrans(tid)
    }

    override suspend fun insertT(transaksi: Transaksi) {
        kasindiDao.insertTrans(transaksi)
    }

    override suspend fun deleteT(transaksi: Transaksi) {
        kasindiDao.deleteTrans(transaksi)
    }

    override suspend fun updateT(transaksi: Transaksi) {
        kasindiDao.updateTrans(transaksi)
    }
}