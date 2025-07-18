package com.example.binreader.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
fun BinInfoCard(binInfo: BinInfo, modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(Modifier.padding(horizontal = 8.dp)) {
            if (binInfo.binNumber != null)
                BinInfoRow("BIN number: ${binInfo.binNumber}", Icons.Default.Search)
            if (binInfo.country?.name != null)
                BinInfoRow("Country: ${binInfo.country.name}", Icons.Default.Home)
            // TODO: open map on coordinates tap
            if (binInfo.country?.latitude != null && binInfo.country.longitude != null)
                BinInfoRow("Coordinates: ${binInfo.country.latitude}:${binInfo.country.longitude}", Icons.Default.LocationOn)
            if (binInfo.type != null)
                BinInfoRow("Type: ${binInfo.type}", Icons.Default.Build)
            if (binInfo.bank?.name != null)
                BinInfoRow("Bank: ${binInfo.bank.name}", Icons.Default.AccountCircle)
            // TODO: open url
            if (binInfo.bank?.url != null)
                BinInfoRow("Bank's website: ${binInfo.bank.url}", Icons.Default.Share)
            // TODO: open caller
            if (binInfo.bank?.phone != null)
                BinInfoRow("Bank's phone: ${binInfo.bank.phone }", Icons.Default.Call)
            if (binInfo.bank?.city != null)
                BinInfoRow("Bank's city: ${binInfo.bank.city }", Icons.Default.Info)
        }
    }
}

@Composable
private fun BinInfoRow(text: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Row(modifier.padding(vertical = 4.dp)) {
        Icon(icon, "")
        Text(text, Modifier.padding(start = 4.dp))
    }
}