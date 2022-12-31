package com.adiliqbal.finize.network.extensions

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

internal enum class NotionDateFilter(val key: String) {
	FROM("after"),
	TO("before"),
	EQUALS("equals")
}

internal enum class NotionTextFilter(val key: String) {
	CONTAINS("contains"),
	EQUALS("equals")
}

internal fun String.toNotionTextFilter(
	property: String,
	type: NotionTextFilter = NotionTextFilter.CONTAINS
) = buildJsonObject {
	put("property", JsonPrimitive(property))
	put("text", buildJsonObject {
		put(type.key, JsonPrimitive(this@toNotionTextFilter))
	})
}

internal fun ID.toNotionRelationFilter(property: String) = buildJsonObject {
	put("property", JsonPrimitive(property))
	put("relation", buildJsonObject {
		put("contains", JsonPrimitive(this@toNotionRelationFilter))
	})
}

internal fun String.toNotionTagFilter(property: String) = buildJsonObject {
	put("property", JsonPrimitive(property))
	put("select", buildJsonObject {
		put("equals", JsonPrimitive(this@toNotionTagFilter))
	})
}

internal fun ID.toNotionTagsFilter(property: String) = buildJsonObject {
	put("property", JsonPrimitive(property))
	put("multi_select", buildJsonObject {
		put("contains", JsonPrimitive(this@toNotionTagsFilter))
	})
}

internal fun LocalDate.toNotionDateFilter(
	property: String,
	type: NotionDateFilter = NotionDateFilter.EQUALS
) = buildJsonObject {
	put("property", JsonPrimitive(property))
	put("date", buildJsonObject {
		put(type.key, JsonPrimitive(this@toNotionDateFilter.toString()))
	})
}