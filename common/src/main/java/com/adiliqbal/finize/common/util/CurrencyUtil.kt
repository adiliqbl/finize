package com.adiliqbal.finize.common.util

import java.util.*

object CurrencyUtil {

	val default get() = Currency.getInstance(Locale.getDefault())

	fun of(code: String) = Currency.getInstance(code)
}