package com.example.binreader.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.binreader.model.BinInfo
import com.example.binreader.ui.ScreenLoadingState
import com.example.binreader.ui.common.BinInfoCard
import com.example.binreader.ui.common.ScreenLoadingStateResolver

@Composable
fun ResultsScreen(binInfo: BinInfo? = null, screenLoadingState: ScreenLoadingState) {
    ScreenLoadingStateResolver(screenLoadingState) {
        if (binInfo != null)
            Column(Modifier.padding(all = 16.dp)) {
                BinInfoCard(binInfo)
            }
        if (binInfo == null && screenLoadingState == ScreenLoadingState.Successful) {
            Text("Cannot find the information. Check if BIN was correct.")
        }
    }
}