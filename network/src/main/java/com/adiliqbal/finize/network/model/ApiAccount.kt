package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionAccountSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable(with = NotionAccountSerializer::class)
data class ApiAccount(
	val id: ID,
	val name: String,
	val currentBalance: Double = 0.0,
	val startingBalance: Double = 0.0,
	val createdAt: Instant? = null
)