package com.example.binreader.data

import com.example.binreader.model.BinBank
import com.example.binreader.model.BinCountry
import com.example.binreader.model.BinInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay

interface BinInfoRepository {
    suspend fun getBinInfo(bin: String): BinInfo?

}

class MockBinInfoRepository: BinInfoRepository {
    override suspend fun getBinInfo(bin: String): BinInfo {
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

class NetworkBinInfoRepository(private val httpClient: HttpClient): BinInfoRepository {
    private val baseUrl = "https://lookup.binlist.net"

    override suspend fun getBinInfo(bin: String): BinInfo? = httpClient.get("${baseUrl}/${bin}").body()
}