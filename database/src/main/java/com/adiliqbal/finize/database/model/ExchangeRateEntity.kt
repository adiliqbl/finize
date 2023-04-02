package com.adiliqbal.finize.database.model

import androidx.room.Entity
import kotlinx.datetime.Instant

@Entity(
    tableName = "exchange-rates",
    primaryKeys = ["from", "to"]
)
data class ExchangeRateEntity(
    val from: String,
    val to: String,
    val rate: Float,
    val date: Instant
)
