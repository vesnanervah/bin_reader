package com.example.binreader.model

import kotlinx.serialization.Serializable

@Serializable
data class BinCountry(
    val name: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null,
)
