package com.adiliqbal.finize.network.extensions

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiTag
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

internal fun String.toNotionTitle() = buildJsonObject {
	put("title", buildJsonArray {
		addJsonObject {
			put("text", buildJsonObject {
				put("content", JsonPrimitive(this@toNotionTitle))
			})
		}
	})
}

internal fun String.toNotionString() = buildJsonObject {
	put("text", JsonPrimitive(this@toNotionString))
}

internal fun String.toNotionRichString() = buildJsonObject {
	put("rich_text", buildJsonArray {
		addJsonObject {
			put("text", buildJsonObject {
				put("content", JsonPrimitive(this@toNotionRichString))
			})
		}
	})
}

internal fun Double.toNotionNumber() = buildJsonObject {
	put("number", JsonPrimitive(this@toNotionNumber))
}

internal fun LocalDate.toNotionDate() = buildJsonObject {
	put("date", buildJsonObject {
		put("start", JsonPrimitive(this@toNotionDate.toString()))
	})
}

internal fun ID.toNotionRelation() = buildJsonObject {
	put("relation", buildJsonArray {
		addJsonObject { put("id", JsonPrimitive(this@toNotionRelation)) }
	})
}

internal fun List<ID>.toNotionRelation() = buildJsonObject {
	put("relation", buildJsonArray {
		this@toNotionRelation.forEach {
			addJsonObject { put("id", JsonPrimitive(it)) }
		}
	})
}

internal fun ApiTag.toNotionTag() = buildJsonObject {
	put("select", buildJsonArray {
		addJsonObject { put("id", JsonPrimitive(id)) }
	})
}

internal fun List<ApiTag>.toNotionTags() = buildJsonObject {
	put("multi_select", buildJsonArray {
		this@toNotionTags.forEach {
			addJsonObject { put("id", JsonPrimitive(it.id)) }
		}
	})
}
