package com.example.binreader.ui.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.binreader.ui.ScreenLoadingState

@Composable
fun ScreenLoadingStateResolver(loadingState: ScreenLoadingState,  onSuccess: @Composable () -> Unit) {
    when(loadingState) {
        ScreenLoadingState.Pending -> CircularProgressIndicator()
        // TODO: reload button maybe
        ScreenLoadingState.Error -> Text("Something went wrong")
        ScreenLoadingState.Successful -> onSuccess()
    }
}