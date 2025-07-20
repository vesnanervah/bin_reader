package com.example.binreader.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// TODO: DI via Dagger
interface AppContainer {
    val binInfoRepository: BinInfoRepository
    val binSearchHistoryRepository: BinSearchHistoryRepository
}

class MockAppContainer: AppContainer  {
    override val binInfoRepository: BinInfoRepository = MockBinInfoRepository()
    override val binSearchHistoryRepository: BinSearchHistoryRepository = MockBinSearchHistoryRepository()
}

class RealAppContainer() : AppContainer {
    private val httpClient = HttpClient(Android) {
       install(ContentNegotiation) {
           json(
               Json {
                   prettyPrint = true
                   isLenient = true
                   ignoreUnknownKeys = true
               }
           )
       }
    }

    override val binInfoRepository: BinInfoRepository by lazy { NetworkBinInfoRepository(httpClient) }
    // TODO: Real repository
    override val binSearchHistoryRepository: BinSearchHistoryRepository = MockBinSearchHistoryRepository()

}