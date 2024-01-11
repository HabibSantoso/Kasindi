package com.example.kasindi

import android.app.Application
import com.example.kasindi.repositori.ContainerApp
import com.example.kasindi.repositori.ContainerDataApp

class AplikasiKasindi : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}