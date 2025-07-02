package com.example.binreader.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.binreader.model.BinInfo
import com.example.binreader.ui.ScreenLoadingState
import com.example.binreader.ui.common.BinInfoCard
import com.example.binreader.ui.common.ScreenLoadingStateResolver

@Composable
fun HistoryScreen(
    binSearchHistory: List<BinInfo>? = null,
    screenLoadingState: ScreenLoadingState,
) {
    ScreenLoadingStateResolver(screenLoadingState) {
        if(binSearchHistory != null)
        LazyColumn {
            items(binSearchHistory) { BinInfoCard(it, Modifier.padding(all = 16.dp)) }
        }
    }
}
