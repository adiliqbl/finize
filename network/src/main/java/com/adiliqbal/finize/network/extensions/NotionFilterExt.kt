package com.adiliqbal.finize.network.extensions

import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
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

internal fun TransactionsFilter.toNotionFilter(): JsonElement? {
	val filters = buildJsonArray {
		if (!toAccount.isNullOrEmpty() && toAccount == fromAccount) {
			add(buildJsonObject {
				put("or", buildJsonArray {
					add(toAccount!!.toNotionRelationFilter(NotionTransactionSerializer.TO_ACCOUNT))
					add(toAccount!!.toNotionRelationFilter(NotionTransactionSerializer.FROM_ACCOUNT))
				})
			})
		} else if (!toAccount.isNullOrEmpty() && !fromAccount.isNullOrEmpty()) {
			add(buildJsonObject {
				put("and", buildJsonArray {
					add(toAccount!!.toNotionRelationFilter(NotionTransactionSerializer.TO_ACCOUNT))
					add(fromAccount!!.toNotionRelationFilter(NotionTransactionSerializer.FROM_ACCOUNT))
				})
			})
		} else if (!toAccount.isNullOrEmpty() && fromAccount.isNullOrEmpty()) {
			add(toAccount!!.toNotionRelationFilter(NotionTransactionSerializer.TO_ACCOUNT))
		} else if (!fromAccount.isNullOrEmpty() && toAccount.isNullOrEmpty()) {
			add(fromAccount!!.toNotionRelationFilter(NotionTransactionSerializer.FROM_ACCOUNT))
		}

		if (type != null) add(type!!.toNotionTypeFilter(NotionTransactionSerializer.TYPE))
		if (budget != null) add(budget!!.toNotionRelationFilter(NotionTransactionSerializer.BUDGET))

		if (date != null) {
			add(date!!.toNotionDateFilter(NotionTransactionSerializer.DATE))
		} else if (dateFrom != null && dateTo != null) {
			add(buildJsonObject {
				put("and", buildJsonArray {
					add(
						dateFrom!!.toNotionDateFilter(
							NotionTransactionSerializer.DATE,
							NotionDateFilter.FROM
						)
					)
					add(
						dateTo!!.toNotionDateFilter(
							NotionTransactionSerializer.DATE,
							NotionDateFilter.TO
						)
					)
				})
			})
		} else if (dateFrom != null) {
			add(
				dateFrom!!.toNotionDateFilter(
					NotionTransactionSerializer.DATE,
					NotionDateFilter.FROM
				)
			)
		} else if (dateTo != null) {
			add(
				dateTo!!.toNotionDateFilter(
					NotionTransactionSerializer.DATE,
					NotionDateFilter.TO
				)
			)
		}

		if (!name.isNullOrEmpty()) {
			add(
				name!!.toNotionTextFilter(
					NotionTransactionSerializer.NAME,
					NotionTextFilter.CONTAINS
				)
			)
		}
	}

	return if (filters.isEmpty()) null
	else if (filters.size == 1) filters[0]
	else buildJsonObject { put("and", filters) }
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

internal fun TransactionType.toNotionTypeFilter(property: String) = buildJsonObject {
	put("property", JsonPrimitive(property))
	put("select", buildJsonObject {
		put("equals", JsonPrimitive(this@toNotionTypeFilter.value))
	})
}

fun String.toNotionTagFilter(property: String) = buildJsonObject {
	put("property", JsonPrimitive(property))
	put("multi_select", buildJsonObject {
		put("contains", JsonPrimitive(this@toNotionTagFilter))
	})
}

internal fun List<String>.toNotionTagsFilter(property: String) = run {
	if (size == 1) {
		get(0).toNotionTagFilter(property)
	} else {
		buildJsonObject {
			put("or", buildJsonArray {
				this@toNotionTagsFilter.map { add(it.toNotionTagFilter(property)) }
			})
		}
	}
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