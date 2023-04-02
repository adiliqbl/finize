package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.database.model.ExchangeRateEntity
import com.adiliqbal.finize.network.model.ApiExchangeRate
import junit.framework.TestCase.assertEquals
import kotlinx.datetime.Instant
import org.junit.Test

class ExchangeRateConversionTest {

	@Test
	fun apiToEntity() {
		val api = ApiExchangeRate(25f, Instant.DISTANT_FUTURE)
		val entity = api.toEntity(from = "from", to = "to")

		assertEquals(api.rate, entity.rate)
		assertEquals(api.date, entity.date)
	}

	@Test
	fun entityToModel() {
		val entity = ExchangeRateEntity("from", "to", 25f, Instant.DISTANT_FUTURE)
		val model = entity.toModel()

		assertEquals(entity.rate, model.rate)
		assertEquals(entity.date, model.date)
	}
}
