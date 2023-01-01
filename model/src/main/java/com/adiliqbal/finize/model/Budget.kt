package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

data class Budget(
	val id: ID,
	val name: String,
	val spent: Double = 0.0,
	val maximum: Double = 0.0,
	val createdAt: Instant? = null
)