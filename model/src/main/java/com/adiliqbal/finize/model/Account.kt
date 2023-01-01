package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class Account(
	val id: ID,
	val name: String,
	val currentBalance: Double = 0.0,
	val startingBalance: Double = 0.0,
	val createdAt: Instant? = null
)