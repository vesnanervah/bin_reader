package com.example.binreader.ui

import com.example.binreader.model.BinInfo

data class BinReaderAppUiState(
    val currentSearch: String? = null,
    val searchHistory: List<BinInfo> = emptyList<BinInfo>(),
    val searchResult: BinInfo? = null,
    )
