package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.common.util.Currencies
import com.adiliqbal.finize.database.model.MoneyEntity
import com.adiliqbal.finize.model.Money
import com.adiliqbal.finize.network.model.ApiMoney
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MoneyConversionTest {

	@Test
	fun modelToApi() {
		val model = Money(25.0, Currencies.of("USD"))
		val api = model.toApi()

		assertEquals(model.amount, api.amount)
		assertEquals(model.currency.currencyCode, api.currency)
	}

	@Test
	fun apiToEntity() {
		val api = ApiMoney(25.0, "USD")
		val entity = api.toEntity()

		assertEquals(api.amount, entity.amount)
		assertEquals(api.currency, entity.currency)
	}

	@Test
	fun entityToModel() {
		val entity = MoneyEntity(25.0, "USD")
		val model = entity.toModel()

		assertEquals(entity.amount, model.amount)
		assertEquals(entity.currency, model.currency.currencyCode)
	}
}
