package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiExchangeRate

interface ForexService {

    suspend fun getExchangeRate(from: String, to: String, refresh: Boolean = false): ApiExchangeRate
}
