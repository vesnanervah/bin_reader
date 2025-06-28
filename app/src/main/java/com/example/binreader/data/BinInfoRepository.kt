package com.example.binreader.data

import com.example.binreader.model.BinBank
import com.example.binreader.model.BinCountry
import com.example.binreader.model.BinInfo
import kotlinx.coroutines.delay

interface BinInfoRepository {
    suspend fun getBinInfo(binNumber: String): BinInfo

}

class MockBinInfoRepository: BinInfoRepository {
    override suspend fun getBinInfo(binNumber: String): BinInfo {
        delay(2000)
        return BinInfo(
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
    }
}

// TODO: Implement repo, provide network service and get data
class NetworkBinInfoRepository: BinInfoRepository {
    override suspend fun getBinInfo(binNumber: String): BinInfo {
        TODO("Not yet implemented")
    }
}