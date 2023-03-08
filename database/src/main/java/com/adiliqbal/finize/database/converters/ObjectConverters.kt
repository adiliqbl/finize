package com.adiliqbal.finize.database.converters

import androidx.room.TypeConverter
import java.util.*

internal class ObjectConverters {

	@TypeConverter
	fun toCurrency(value: String?): Currency? = value?.let(Currency::getInstance)

	@TypeConverter
	fun fromCurrency(currency: Currency?): String? = currency?.currencyCode
}
