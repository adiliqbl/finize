package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.model.ExchangeRate

interface ForexService {

    suspend fun getExchangeRate(isoFrom: String, isoTo: String): ExchangeRate
}
