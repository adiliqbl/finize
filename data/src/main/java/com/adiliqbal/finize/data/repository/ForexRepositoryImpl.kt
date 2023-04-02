package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.database.dao.ExchangeRateDao
import com.adiliqbal.finize.database.model.ExchangeRateEntity
import com.adiliqbal.finize.network.service.ForexService
import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.until

internal class ForexRepositoryImpl @Inject constructor(
    private val exchangeRateDao: ExchangeRateDao,
    private val forexService: ForexService
) : ForexRepository {

    override suspend fun getExchangeRate(fromIso: String, toIso: String, refresh: Boolean) =
        flow {
            val cached = exchangeRateDao.get(fromIso = fromIso, toIso = toIso)
            if (!refresh && cached != null && cached.date.until(DateUtil.now(), DateTimeUnit.HOUR) <= 23) {
                emit(cached.toModel())
            } else {
                val rate = forexService.getExchangeRate(from = fromIso, to = toIso)
                val newEntity = rate.toEntity(from = fromIso, to = toIso)
                exchangeRateDao.insert(
                    newEntity,
                    ExchangeRateEntity(
                        from = toIso,
                        to = fromIso,
                        rate = 1 / rate.rate,
                        date = rate.date
                    )
                )
                emit(newEntity.toModel())
            }
        }

    override suspend fun reset() = exchangeRateDao.clear()
}
