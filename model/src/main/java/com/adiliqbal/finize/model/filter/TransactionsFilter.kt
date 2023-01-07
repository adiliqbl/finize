package com.adiliqbal.finize.model.filter

import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate

data class TransactionsFilter(
	val name: String? = null,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val type: TransactionType? = null,
	val tags: List<String>? = null,
	val budget: ID? = null,
	val date: LocalDate? = null,
	val dateFrom: LocalDate? = null,
	val dateTo: LocalDate? = null
)