package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionBudgetSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable(with = NotionBudgetSerializer::class)
data class ApiBudget(
	val id: ID,
	val name: String,
	val spent: Double = 0.0,
	val maximum: Double = 0.0,
	val createdAt: Instant? = null
)