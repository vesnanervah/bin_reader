package com.example.binreader.model

data class BinInfo(
    val binNumber: String? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val country: BinCountry? = null,
    val bank: BinBank? = null,
)
