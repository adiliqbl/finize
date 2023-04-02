package com.adiliqbal.finize.model

import kotlinx.datetime.Instant

data class ExchangeRate(
    val from: String,
    val to: String,
    val rate: Float,
    val date: Instant
)
