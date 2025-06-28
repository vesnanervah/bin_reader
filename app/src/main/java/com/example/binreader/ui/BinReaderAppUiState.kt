package com.example.binreader.ui

import com.example.binreader.model.BinBank
import com.example.binreader.model.BinCountry

data class BinReaderAppUiState(
    val selectedBin: String? = null,
    val binHistory: List<String> = emptyList<String>(),
    val country: BinCountry? = null,
    val bank: BinBank? = null,
)
