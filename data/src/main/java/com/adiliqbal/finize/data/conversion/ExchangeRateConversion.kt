package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.database.model.ExchangeRateEntity
import com.adiliqbal.finize.model.ExchangeRate
import com.adiliqbal.finize.network.model.ApiExchangeRate

internal fun ApiExchangeRate.toEntity(from: String, to: String) =
    ExchangeRateEntity(from, to, rate, date)

internal fun ExchangeRateEntity.toModel() = ExchangeRate(from, to, rate, date)
