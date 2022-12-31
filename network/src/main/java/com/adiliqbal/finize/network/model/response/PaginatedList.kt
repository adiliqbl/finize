package com.adiliqbal.finize.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedList<T>(
	val results: List<T>,
	@SerialName("next_cursor") val next: String? = null,
	@SerialName("has_more") val hasMore: Boolean = false
)