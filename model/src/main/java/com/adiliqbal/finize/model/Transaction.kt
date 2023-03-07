package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import java.util.*

data class Transaction(
	val id: ID,
	val name: String,
	val category: List<String>,
	val accountTo: ID? = null,
	val accountFrom: ID? = null,
	val budget: ID? = null,
	val amount: Money,
	val amountTo: Money? = null,
	val amountFrom: Money? = null,
	val amountLocal: Money,
	val task: ID? = null,
	val note: String? = null,
	val currency: Currency,
	val date: Instant
)