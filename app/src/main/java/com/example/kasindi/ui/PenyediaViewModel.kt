package com.example.kasindi.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kasindi.AplikasiKasindi
import com.example.kasindi.ui.view.viewmodel.AddTransViewModel
import com.example.kasindi.ui.view.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiMars().container.repositoriKasindi)
        }

        initializer {
            AddTransViewModel(aplikasiMars().container.repositoriKasindi)
        }
    }
}

fun CreationExtras.aplikasiMars(): AplikasiKasindi =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKasindi)