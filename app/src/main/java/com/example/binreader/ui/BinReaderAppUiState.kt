package com.example.binreader.ui

import com.example.binreader.model.BinInfo

enum class ScreenLoadingState { Pending, Successful, Error }

data class BinReaderAppUiState(
    val currentSearch: String? = null,
    val searchHistory: List<BinInfo> = emptyList<BinInfo>(),
    val searchResult: BinInfo? = null,
    val historyScreenLoadingState: ScreenLoadingState = ScreenLoadingState.Pending,
    val resultsScreenLoadingState: ScreenLoadingState = ScreenLoadingState.Pending,
    )
