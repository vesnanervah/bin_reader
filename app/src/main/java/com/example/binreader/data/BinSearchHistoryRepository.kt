package com.example.binreader.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.binreader.model.BinBank
import com.example.binreader.model.BinCountry
import com.example.binreader.model.BinInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json


interface BinSearchHistoryRepository {

    val searchHistoryStateFlow: Flow<List<BinInfo>>

    suspend fun addBinSearchToHistory(binInfo: BinInfo)
}

class MockBinSearchHistoryRepository: BinSearchHistoryRepository {
    private val searchHistoryFlow: MutableStateFlow<List<BinInfo>> = MutableStateFlow(
        listOf(
            BinInfo(
                scheme = "visa",
                type = "debit",
                brand = "Visa/Dankort",
                country = BinCountry(
                    name = "Denmark",
                    latitude = 56,
                    longitude = 10
                ),
                bank = BinBank(
                    name = "Jyske Bank",
                    url = "www.jyskebank.dk",
                    phone = "+4589893300",
                    city = "Hjørring"
                )
            )
        )
    )

    override val searchHistoryStateFlow = searchHistoryFlow.asStateFlow()

    override suspend fun addBinSearchToHistory(binInfo: BinInfo) {
        delay(1000)
        searchHistoryFlow.update { it.plus(binInfo) }
    }
}


class LocalBinSearchHistoryRepository(private val dataStore: DataStore<Preferences>): BinSearchHistoryRepository {
    private val storageKey = stringPreferencesKey("bins")
    private val jsonFactory = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    override val searchHistoryStateFlow = dataStore.data.map {
        decodeHistoryFromPreferences(it)
    }

    private fun decodeHistoryFromPreferences(p: Preferences): List<BinInfo> {
        val hasStoredData = p.contains(storageKey)
        if (!hasStoredData) {
            return emptyList<BinInfo>()
        }
        val value = p[storageKey]
        if((value ?: "").isEmpty()) return emptyList<BinInfo>()
        return jsonFactory.decodeFromString<List<BinInfo>>(value!!)
    }

    override suspend fun addBinSearchToHistory(binInfo: BinInfo) {
        dataStore.updateData {
            val history = decodeHistoryFromPreferences(it)
            val preference = it.toMutablePreferences()
            val filteredHistory = history.filter { savedBin -> savedBin.binNumber != binInfo.binNumber }
            val encodedHistory = jsonFactory.encodeToString(filteredHistory.plus(binInfo))
            preference[storageKey] = encodedHistory
            preference
        }
    }
}