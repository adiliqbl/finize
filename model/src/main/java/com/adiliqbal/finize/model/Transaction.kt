package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class Transaction(
    val id: ID = "",
    val name: String,
    val amount: Money,
    val amountTo: Money? = null,
    val amountFrom: Money? = null,
    val amountLocal: Money? = null,
    val accountTo: ID? = null,
    val accountFrom: ID? = null,
    val budget: ID? = null,
    val task: ID? = null,
    val note: String? = null,
    val categories: List<String> = emptyList(),
    val date: Instant
)
