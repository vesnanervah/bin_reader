package com.example.binreader.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.binreader.model.BinInfo

@Composable
fun HistoryScreen(
    binSearchHistory: List<BinInfo>,
) {
    LazyColumn {
        items(binSearchHistory) { SearchHistoryItem(it, Modifier.padding(all = 16.dp))}
    }
}

// TODO: maybe replace in separate file in order to use in search results page
@Composable
fun SearchHistoryItem(binInfo: BinInfo, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(Modifier.padding(horizontal = 8.dp)) {
            if (binInfo.binNumber != null)
                BinInfoRow("BIN number: ${binInfo.binNumber}", Icons.Default.Search)
            if (binInfo.country?.name != null)
                BinInfoRow("Country: ${binInfo.country.name}", Icons.Default.Home)
            // TODO: open map on coordinates tap
            if (binInfo.country?.latitude != null && binInfo.country?.longitude != null)
                BinInfoRow("Coordinates: ${binInfo.country?.latitude}:${binInfo.country?.longitude}", Icons.Default.LocationOn)
            if (binInfo.type != null)
                BinInfoRow("Type: ${binInfo.type}", Icons.Default.Build)
            if (binInfo.bank?.name != null)
                BinInfoRow("Bank: ${binInfo.bank?.name}", Icons.Default.AccountCircle)
            if (binInfo.bank?.url != null)
                BinInfoRow("Bank's website: ${binInfo.bank?.url}", Icons.Default.Share)
            if (binInfo.bank?.phone != null)
                BinInfoRow("Bank's website: ${binInfo.bank?.phone }", Icons.Default.Call)
            if (binInfo.bank?.city != null)
                BinInfoRow("Bank's website: ${binInfo.bank?.city }", Icons.Default.Info)
        }
    }
}


@Composable
private fun BinInfoRow(text: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Row(modifier.padding(vertical = 4.dp)) {
        Text(text, Modifier.padding(end = 4.dp))
        Icon(icon, "")
    }
}