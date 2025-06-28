package com.example.binreader.data

import com.example.binreader.model.BinBank
import com.example.binreader.model.BinCountry
import com.example.binreader.model.BinInfo
import kotlinx.coroutines.delay

interface BinSearchHistoryRepository {
    suspend fun getBinSearchHistory(): List<BinInfo>
}

class MockBinSearchHistoryRepository: BinSearchHistoryRepository {
    override suspend fun getBinSearchHistory(): List<BinInfo> {
        delay(2000)
        return listOf(
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
            ),
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
    }
}


// TODO: Implement local repo, provide local storage and get actual history
class LocalBinSearchHistoryRepository: BinSearchHistoryRepository {
    override suspend fun getBinSearchHistory(): List<BinInfo> {
        TODO("Not yet implemented")
    }
}