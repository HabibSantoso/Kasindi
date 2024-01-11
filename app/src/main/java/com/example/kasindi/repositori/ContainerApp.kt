package com.example.kasindi.repositori

import android.content.Context
import com.example.kasindi.model.DatabaseKasindi

interface ContainerApp {
    val repositoriKasindi : RepositoriKasindi
}

class ContainerDataApp(private val  context: Context) : ContainerApp{
    override val repositoriKasindi: RepositoriKasindi by lazy {
        OfflineRepositoriKasindi(DatabaseKasindi.getDatabase(context).kasindiDao())
    }
}