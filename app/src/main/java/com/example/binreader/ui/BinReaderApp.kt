package com.example.binreader.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

enum class BinReaderAppScreen {
    Start, Results, History
}

@Composable
fun BinReaderApp(
    viewModel: BinReaderAppViewModel = viewModel(factory = BinReaderAppViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {

}