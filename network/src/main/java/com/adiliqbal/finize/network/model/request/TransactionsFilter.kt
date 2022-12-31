package com.adiliqbal.finize.network.model.request

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionTransactionFilterSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable(with = NotionTransactionFilterSerializer::class)
data class TransactionsFilter(
	val name: String? = null,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val type: ID? = null,
	val budget: ID? = null,
	val date: LocalDate? = null,
	val dateFrom: LocalDate? = null,
	val dateTo: LocalDate? = null,

	val cursor: String? = null,
	val pageSize: Int = 30
)