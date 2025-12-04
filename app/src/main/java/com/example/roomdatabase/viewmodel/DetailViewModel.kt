package com.example.roomdatabase.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.repositori.RepositoriSiswa
import com.example.roomdatabase.view.route.DestinasiDetailSiswa
import kotlinx.coroutines.flow.*

/**
 * ViewModel untuk halaman Detail Siswa
 */
class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    // Ambil ID siswa dari navigation arguments
    private val siswaId: Int = checkNotNull(savedStateHandle[DestinasiDetailSiswa.siswaIdArg])

    /**
     * UI State untuk halaman Detail.
     * Mengambil data siswa berdasarkan ID dari repository.
     */
    val uiDetailState: StateFlow<DetailSiswaUiState> =
        repositoriSiswa.getSiswaStream(siswaId)
            .filterNotNull()
            .map {
                DetailSiswaUiState(detailSiswa = it.toDetailSiswa())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailSiswaUiState()
            )

    /**
     * Hapus siswa dari database
     */
    suspend fun deleteSiswa() {
        repositoriSiswa.deleteSiswa(uiDetailState.value.detailSiswa.toSiswa())
    }
}

/**
 * UI State untuk Detail Siswa
 */
data class DetailSiswaUiState(
    val detailSiswa: DetailSiswa = DetailSiswa()
)

