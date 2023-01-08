package com.adiliqbal.finize.model.filter

import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class TransactionsFilter(
	val name: String? = null,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val type: TransactionType? = null,
	val tags: List<String>? = null,
	val budget: ID? = null,
	val date: Instant? = null,
	val dateFrom: Instant? = null,
	val dateTo: Instant? = null
)