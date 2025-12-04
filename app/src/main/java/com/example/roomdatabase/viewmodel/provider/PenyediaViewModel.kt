package com.example.roomdatabase.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomdatabase.repositori.AplikasiSiswa
import com.example.roomdatabase.viewmodel.HomeViewModel
import com.example.roomdatabase.viewmodel.EntryViewModel
import com.example.roomdatabase.viewmodel.DetailViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {

        // ViewModel untuk halaman Home
        initializer {
            HomeViewModel(
                repositorySiswa = aplikasiSiswa().container.repositoriSiswa
            )
        }

        // ViewModel untuk halaman Entry
        initializer {
            EntryViewModel(
                repositoriSiswa = aplikasiSiswa().container.repositoriSiswa
            )
        }

        // ViewModel untuk halaman Detail
        initializer {
            DetailViewModel(
                savedStateHandle = this.createSavedStateHandle(),
                repositoriSiswa = aplikasiSiswa().container.repositoriSiswa
            )
        }
    }
}

/**
 * Extension untuk mengambil instance AplikasiSiswa dari CreationExtras.
 */
fun CreationExtras.aplikasiSiswa(): AplikasiSiswa =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiSiswa