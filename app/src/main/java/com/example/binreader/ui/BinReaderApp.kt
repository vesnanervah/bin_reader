package com.example.binreader.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.binreader.ui.screen.HistoryScreen
import com.example.binreader.ui.screen.ResultsScreen
import com.example.binreader.ui.screen.StartScreen

enum class BinReaderAppScreen {
    Start, Results, History
}

@Composable
fun BinReaderApp(
    viewModel: BinReaderAppViewModel = viewModel(factory = BinReaderAppViewModel.Factory),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentScreen = BinReaderAppScreen.valueOf(backStackEntry.value?.destination?.route ?: BinReaderAppScreen.Start.name)

    val uiState = viewModel.uiState.collectAsState()

    val appBarTitle = when (currentScreen) {
        BinReaderAppScreen.Results -> "Results"
        BinReaderAppScreen.History -> "History"
        else -> null
    }
    val canNavigateUp = currentScreen != BinReaderAppScreen.Start


    Scaffold(
        topBar = { AppTopBar(appBarTitle, canNavigateUp) { navController.navigateUp() } }
    ) {
        Surface(Modifier.fillMaxSize().padding(it)) {
            NavHost(
                navController,
                currentScreen.name
            ) {
                composable(BinReaderAppScreen.Start.name) {
                    StartScreen(onHistoryTap = {
                        viewModel.loadHistory()
                        navController.navigate(BinReaderAppScreen.History.name)
                    }) {
                        viewModel.loadInfo(it)
                        navController.navigate(BinReaderAppScreen.Results.name)
                    }
                }
                composable(BinReaderAppScreen.History.name) {
                    HistoryScreen(uiState.value.searchHistory, uiState.value.historyScreenLoadingState)
                }
                composable(BinReaderAppScreen.Results.name) {
                    ResultsScreen(uiState.value.searchResult, uiState.value.resultsScreenLoadingState)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(title: String?, canNavigateUp: Boolean, navigateUp: () -> Unit) {
    TopAppBar(
        title = {
            if (title != null) Text(title)
        },
        navigationIcon = {
          if (canNavigateUp) Button(navigateUp) {
              Icon(Icons.Default.ArrowBack, "Go back")
          }
        },
    )
}