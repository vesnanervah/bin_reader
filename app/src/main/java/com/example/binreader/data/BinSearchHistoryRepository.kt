package com.example.binreader.data

import kotlinx.coroutines.delay

interface BinSearchHistoryRepository {
    suspend fun getBinSearchHistory(): List<String>
}

class MockBinSearchHistoryRepository: BinSearchHistoryRepository {
    override suspend fun getBinSearchHistory(): List<String> {
        delay(2000)
        return listOf("4571 7360", "3642 6451")
    }
}


// TODO: Implement local repo, provide local storage and get actual history
class LocalBinSearchHistoryRepository: BinSearchHistoryRepository {
    override suspend fun getBinSearchHistory(): List<String> {
        TODO("Not yet implemented")
    }
}