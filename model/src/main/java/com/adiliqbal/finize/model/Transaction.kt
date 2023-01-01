package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate

data class Transaction(
	val id: ID,
	val name: String,
	val type: Tag? = null,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val budgets: List<ID>? = null,
	val tags: List<Tag>? = null,
	val note: String? = null,
	val date: LocalDate
)