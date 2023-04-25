package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface ForexRepository {
    suspend fun getExchangeRate(fromIso: String, toIso: String, refresh: Boolean = false): Flow<ExchangeRate>
    suspend fun reset()
}
