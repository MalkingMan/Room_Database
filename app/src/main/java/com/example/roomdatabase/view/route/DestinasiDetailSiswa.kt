package com.example.roomdatabase.view.route

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.roomdatabase.R

object DestinasiDetailSiswa : DestinyNavigation {
    override val route = "item_detail"
    override val titleRes = R.string.detail_siswa
    const val siswaIdArg = "siswaId"
    val routeWithArgs = "$route/{$siswaIdArg}"
    val arguments = listOf(
        navArgument(siswaIdArg) {
            type = NavType.IntType
        }
    )
}

