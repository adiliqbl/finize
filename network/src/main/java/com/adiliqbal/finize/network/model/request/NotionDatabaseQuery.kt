package com.adiliqbal.finize.network.model.request

import com.adiliqbal.finize.network.model.enums.SortOrder
import com.adiliqbal.finize.network.model.serializer.NotionDatabaseQuerySerializer
import kotlinx.serialization.Serializable

@Serializable(with = NotionDatabaseQuerySerializer::class)
internal data class NotionDatabaseQuery(
	val sortOrder: SortOrder = SortOrder.DESCENDING,
	val sortField: String? = null,
	val filter: JsonObjectDelegate? = null,
	val cursor: String? = null,
	val pageSize: Int = 20
) {
	internal companion object {
		const val SORT = "sorts"
		const val SORT_FIELD = "property"
		const val SORT_ORDER = "direction"
		const val FILTER = "filter"
		const val CURSOR = "start_cursor"
		const val PAGE_SIZE = "page_size"
	}
}