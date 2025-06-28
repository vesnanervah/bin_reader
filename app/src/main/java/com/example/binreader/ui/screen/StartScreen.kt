package com.example.binreader.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalFocusManager

private val binNumberRegex = Regex("^[0-9]{4}( )?[0-9]{2,4}$")

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onHistoryTap: () -> Unit,
    submit: (binNumber: String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var value by remember { mutableStateOf("")  }
    var showError by remember { mutableStateOf(false) }

    Column(modifier, Arrangement.Center, Alignment.CenterHorizontally) {
        TextField(
            value,
            { value = it },
            isError = showError,
            placeholder = { Text("Enter BIN number") },
            supportingText = { if(showError) Text("BIN is invalid!")}
        )
        Modifier.padding(top = 16.dp)
        Button({
            onSubmitTap(value, focusManager,) {
                showError = it
                if (!it) submit(value)
            }
        }) { Text("Submit") }
        Modifier.padding(top = 8.dp)
        Button(onHistoryTap) {  Text("History")  }
    }
}

private fun onSubmitTap(
    value: String,
    focusManager: FocusManager,
    onValidate: (validationResult: Boolean) -> Unit,
    ) {
    focusManager.clearFocus()
    val validationResult = validateBin(value)
    onValidate(validationResult)
}

private fun validateBin(text: String): Boolean = binNumberRegex.matches(text)