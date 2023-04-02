package com.adiliqbal.finize.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ApiExchangeRate(val rate: Float, val date: Instant)
