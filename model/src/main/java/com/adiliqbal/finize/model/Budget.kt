package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

data class Budget(
	val id: ID,
	val name: String,
	val limit: Double = 0.0,
	val expireAt: LocalDate? = null,
	val createdAt: Instant? = null
)