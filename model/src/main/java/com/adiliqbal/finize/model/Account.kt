package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.enums.AccountType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class Account(
	val id: ID,
	val name: String,
	val balance: Double = 0.0,
	val budget: Budget? = null,
	val type: AccountType,
	val createdAt: Instant? = null
)