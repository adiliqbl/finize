package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.enums.toTransactionType

internal object TransactionTypeSerializer : EnumSerializer<TransactionType>() {
	override fun String?.stringToEnum() = toTransactionType()
}