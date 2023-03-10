package com.adiliqbal.finize.model.filter

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class TransactionsFilter(
	val name: String? = null,
	val accountTo: ID? = null,
	val accountFrom: ID? = null,
	val category: List<String>? = null,
	val budget: ID? = null,
	val date: Instant? = null,
	val dateFrom: Instant? = null,
	val dateTo: Instant? = null
)