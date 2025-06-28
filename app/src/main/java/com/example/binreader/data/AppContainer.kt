package com.example.binreader.data

// TODO: DI via Dagger
interface AppContainer {
    val binInfoRepository: BinInfoRepository
    val binSearchHistoryRepository: BinSearchHistoryRepository
}

class MockAppContainer: AppContainer  {
    override val binInfoRepository: BinInfoRepository = MockBinInfoRepository()
    override val binSearchHistoryRepository: BinSearchHistoryRepository = MockBinSearchHistoryRepository()
}