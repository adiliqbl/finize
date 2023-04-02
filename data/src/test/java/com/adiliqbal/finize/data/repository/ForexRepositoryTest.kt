package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.database.dao.ExchangeRateDao
import com.adiliqbal.finize.database.model.ExchangeRateEntity
import com.adiliqbal.finize.network.model.ApiExchangeRate
import com.adiliqbal.finize.network.service.ForexService
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class ForexRepositoryTest {

    private val dao = Mockito.mock(ExchangeRateDao::class.java)
    private val service = Mockito.mock(ForexService::class.java)
    private val repository = ForexRepositoryImpl(dao, service)

    @Test
    fun `get rate from cache`() = runTest {
        val fromIso = "USD"
        val toIso = "EUR"
        val cached = ExchangeRateEntity(
            fromIso, toIso, 1.23f, DateUtil.now().minus(22, DateTimeUnit.HOUR)
        )

        whenever(dao.get(fromIso, toIso)).thenReturn(cached)

        val result = repository.getExchangeRate(fromIso, toIso, false).last()
        assertEquals(cached.toModel(), result)
    }

    @Test
    fun `get rate from api if cache expired`() = runTest {
        val fromIso = "USD"
        val toIso = "EUR"
        val cached = ExchangeRateEntity(
            fromIso, toIso, 1.23f, DateUtil.now().minus(24, DateTimeUnit.HOUR)
        )
        val freshRate = ApiExchangeRate(5.24f, DateUtil.now())

        whenever(dao.get(fromIso, toIso)).thenReturn(cached)
        whenever(service.getExchangeRate(fromIso, toIso)).thenReturn(freshRate)

        val result = repository.getExchangeRate(fromIso, toIso, false).last()
        assertEquals(freshRate.toEntity(fromIso, toIso).toModel(), result)

        Mockito.verify(dao).insert(
            freshRate.toEntity(fromIso, toIso),
            ExchangeRateEntity(
                from = toIso,
                to = fromIso,
                rate = 1 / freshRate.rate,
                date = freshRate.date
            )
        )
    }

    @Test
    fun `refresh rate from api`() = runTest {
        val fromIso = "USD"
        val toIso = "EUR"
        val cached = ExchangeRateEntity(
            fromIso, toIso, 1.23f, DateUtil.now().minus(1, DateTimeUnit.HOUR)
        )
        val freshRate = ApiExchangeRate(5.24f, DateUtil.now())

        whenever(dao.get(fromIso, toIso)).thenReturn(cached)
        whenever(service.getExchangeRate(fromIso, toIso)).thenReturn(freshRate)

        val result = repository.getExchangeRate(fromIso, toIso, true).last()
        assertEquals(freshRate.toEntity(fromIso, toIso).toModel(), result)

        Mockito.verify(dao).insert(
            freshRate.toEntity(fromIso, toIso),
            ExchangeRateEntity(
                from = toIso,
                to = fromIso,
                rate = 1 / freshRate.rate,
                date = freshRate.date
            )
        )
    }

    @Test
    fun reset() = runTest {
        repository.reset()
        Mockito.verify(dao).clear()
    }
}
