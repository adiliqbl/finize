package com.adiliqbal.finize.common.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class CurrenciesTest {

	@Test
	fun `default currency`() {
		assertEquals(Currency.getInstance(Locale.getDefault()), Currencies.default)
	}

	@Test
	fun `valid currency`() {
		assertEquals(Currency.getInstance("USD"), Currencies.of("USD"))
	}

	@Test(expected = IllegalArgumentException::class)
	fun `invalid currency`() {
		Currencies.of("INVALID-CODE")
	}
}