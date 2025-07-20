package com.example.binreader.model

import kotlinx.serialization.Serializable

@Serializable
data class BinBank(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null,
)
