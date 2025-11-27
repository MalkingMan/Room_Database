package com.example.roomdatabase.view.uicontroller


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable


@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}
