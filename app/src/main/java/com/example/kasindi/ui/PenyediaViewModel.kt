package com.example.kasindi.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kasindi.AplikasiKasindi
import com.example.kasindi.ui.view.viewmodel.AddTransViewModel
import com.example.kasindi.ui.view.viewmodel.DetailTransViewModel
import com.example.kasindi.ui.view.viewmodel.HomeViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplicationKasindi().container.repositoriKasindi)
        }

        initializer {
            AddTransViewModel(aplicationKasindi().container.repositoriKasindi)
        }

        initializer {
            DetailTransViewModel(createSavedStateHandle(),aplicationKasindi().container.repositoriKasindi)
        }
    }
}

fun CreationExtras.aplicationKasindi(): AplikasiKasindi =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiKasindi)