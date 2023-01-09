package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class Transaction(
	val id: ID,
	val name: String,
	val category: List<String>,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val budget: ID? = null,
	val note: String? = null,
	val date: Instant
)