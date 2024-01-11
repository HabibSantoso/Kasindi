package com.example.kasindi.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kasindi.AplikasiKasindi

object PenyediaViewModel {
    val Factory = viewModelFactory {  }
}

fun CreationExtras.aplikasiMars(): AplikasiKasindi =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKasindi)