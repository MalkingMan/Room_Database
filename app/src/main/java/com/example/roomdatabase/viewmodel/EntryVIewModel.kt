package com.example.roomdatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.myroomsatu.repositori.RepositorySiswa
import com.example.myroomsatu.room.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// -------------------------
// DATA CLASS DETAIL SISWA
// -------------------------
data class DetailSiswa(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

// -------------------------
// UI STATE SISWA
// -------------------------
data class UIStateSiswa(
    val detailSiswa: DetailSiswa = DetailSiswa(),
    val isEntryValid: Boolean = false
)

// -------------------------
// EXTENSION: DetailSiswa -> Entity Siswa
// -------------------------
fun DetailSiswa.toSiswa(): Siswa = Siswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)


// -------------------------
// EXTENSION: Entity Siswa -> UIState
// -------------------------
fun Siswa.toUIStateSiswa(isEntryValid: Boolean = false): UIStateSiswa =
    UIStateSiswa(
        detailSiswa = this.toDetailSiswa(),
        isEntryValid = isEntryValid
    )

// -------------------------
// EXTENSION: Entity Siswa -> DetailSiswa
// -------------------------
fun Siswa.toDetailSiswa(): DetailSiswa = DetailSiswa(
    id = id,
    nama = nama,
    alamat = alamat,
    telpon = telpon
)

// -------------------------
// VIEWMODEL
// -------------------------
class EntryViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    /**
     * Berisi status Siswa saat ini
     */
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // -------------------------
    // VALIDASI INPUT
    // -------------------------
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    // -------------------------
    // UPDATE STATE UI
    // -------------------------
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(
                detailSiswa = detailSiswa,
                isEntryValid = validasiInput(detailSiswa)
            )
    }

    // -------------------------
    // SIMPAN DATA
    // -------------------------
    suspend fun saveSiswa() {
        if (validasiInput()) {
            repositorySiswa.insertSiswa(
                siswa = uiStateSiswa.detailSiswa.toSiswa()
            )
        }
    }
}