package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate

data class Transaction(
	val id: ID,
	val name: String,
	val type: TransactionType,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val budget: ID? = null,
	val tags: List<String>? = null,
	val note: String? = null,
	val date: LocalDate
)