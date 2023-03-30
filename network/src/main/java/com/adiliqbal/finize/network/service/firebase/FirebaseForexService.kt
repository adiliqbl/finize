package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.model.ExchangeRate
import com.adiliqbal.finize.network.service.ForexService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseForexService @Inject constructor() : ForexService {

    override suspend fun getExchangeRate(isoFrom: String, isoTo: String): ExchangeRate {
        TODO("Not yet implemented")
    }
}
