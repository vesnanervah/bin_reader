package com.example.binreader.model

import kotlinx.serialization.Serializable

@Serializable
data class BinInfo(
    val binNumber: String? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val country: BinCountry? = null,
    val bank: BinBank? = null,
)
