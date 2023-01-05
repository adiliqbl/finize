package com.adiliqbal.finize.database.converters

import androidx.room.TypeConverter
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.enums.toTransactionType

class EnumConverters {

	@TypeConverter
	fun toTransactionType(value: String?): TransactionType = value.toTransactionType()

	@TypeConverter
	fun fromTransactionType(type: TransactionType) = type.value
}
