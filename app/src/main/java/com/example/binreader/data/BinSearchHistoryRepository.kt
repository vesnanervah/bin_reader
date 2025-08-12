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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json


interface BinSearchHistoryRepository {
    // TODO: replace into field with private setter and some immutable structure as getter
    fun getBinSearchHistoryFlow(): MutableStateFlow<List<BinInfo>>

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

    override fun getBinSearchHistoryFlow(): MutableStateFlow<List<BinInfo>>  {
        return searchHistoryFlow
    }

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
    private val historyFlow: Flow<List<BinInfo>> = dataStore.data.map {
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

    override fun getBinSearchHistoryFlow(): MutableStateFlow<List<BinInfo>> {
        TODO("Not yet implemented")
    }


    override suspend fun addBinSearchToHistory(binInfo: BinInfo) {
        /* dataStore.updateData {
            val current = getBinSearchHistory()
            val preference = it.toMutablePreferences()
            val encodedHistory = jsonFactory.encodeToString(current.plus(binInfo))
            preference[storageKey] = encodedHistory
            preference
        }*/
    }
}