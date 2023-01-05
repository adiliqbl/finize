package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer
import com.adiliqbal.finize.network.model.serializer.TransactionTypeSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable(with = NotionTransactionSerializer::class)
data class ApiTransaction(
	val id: ID,
	val name: String,
	@Serializable(TransactionTypeSerializer::class) val type: TransactionType = TransactionType.UNKNOWN,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val budget: ID? = null,
	val tags: List<String>? = null,
	val note: String? = null,
	val date: LocalDate = DateUtil.currentDay(),
)