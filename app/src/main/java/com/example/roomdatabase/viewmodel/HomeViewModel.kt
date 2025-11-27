package com.example.roomdatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomsatu.repositori.RepositorySiswa
import com.example.myroomsatu.room.Siswa
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    /**
     * UI State untuk halaman Home.
     * Mengambil data siswa dari repository sebagai Flow, lalu diubah menjadi StateFlow.
     */
    val homeUiState: StateFlow<HomeUiState> =
        repositorySiswa.getAllSiswaStream()
            .filterNotNull()
            .map { HomeUiState(listSiswa = it.toList()) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
}

/**
 * Menyimpan data UI yang ditampilkan di HomeScreen.
 */
data class HomeUiState(
    val listSiswa: List<Siswa> = emptyList()
)

