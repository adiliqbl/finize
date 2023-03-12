package com.adiliqbal.finize.model.filter

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class TransactionsFilter(
	val name: String? = null,
	val accountTo: ID? = null,
	val accountFrom: ID? = null,
	val categories: List<String>? = null,
	val budget: ID? = null,
	val dateFrom: Instant? = null,
	val dateTo: Instant? = null
)