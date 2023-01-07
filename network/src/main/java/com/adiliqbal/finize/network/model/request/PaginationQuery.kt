package com.adiliqbal.finize.network.model.request

import com.adiliqbal.finize.model.enums.SortOrder
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

internal interface BasePaginationQuery {
	val sortOrder: SortOrder
	val sortField: String?
	val filter: JsonElement?
	val cursor: String?
	val pageSize: Int
}

@Serializable
data class PaginationQuery(
	override val sortOrder: SortOrder = SortOrder.DESCENDING,
	override val sortField: String? = null,
	override val filter: JsonElement? = null,
	override val cursor: String? = null,
	val page: Int? = null,
	override val pageSize: Int = 20
) : BasePaginationQuery