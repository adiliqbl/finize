package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.extensions.NotionDateFilter
import com.adiliqbal.finize.network.extensions.NotionTextFilter
import com.adiliqbal.finize.network.extensions.toNotionDateFilter
import com.adiliqbal.finize.network.extensions.toNotionRelationFilter
import com.adiliqbal.finize.network.extensions.toNotionTagFilter
import com.adiliqbal.finize.network.extensions.toNotionTextFilter
import com.adiliqbal.finize.network.model.request.TransactionsFilter
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer.BUDGET
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer.DATE
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer.FROM_ACCOUNT
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer.NAME
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer.TO_ACCOUNT
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer.TYPE
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

internal object NotionTransactionFilterSerializer : KSerializer<TransactionsFilter> {
	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("NotionTransactionsFilter", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: TransactionsFilter) = with(value) {
		val filters = buildJsonArray {
			if (!toAccount.isNullOrEmpty() && toAccount == fromAccount) {
				add(buildJsonObject {
					put("or", buildJsonArray {
						add(toAccount.toNotionRelationFilter(TO_ACCOUNT))
						add(toAccount.toNotionRelationFilter(FROM_ACCOUNT))
					})
				})
			} else if (!toAccount.isNullOrEmpty() && !fromAccount.isNullOrEmpty()) {
				add(buildJsonObject {
					put("and", buildJsonArray {
						add(toAccount.toNotionRelationFilter(TO_ACCOUNT))
						add(fromAccount.toNotionRelationFilter(FROM_ACCOUNT))
					})
				})
			} else if (!toAccount.isNullOrEmpty() && fromAccount.isNullOrEmpty()) {
				add(toAccount.toNotionRelationFilter(TO_ACCOUNT))
			} else if (!fromAccount.isNullOrEmpty() && toAccount.isNullOrEmpty()) {
				add(fromAccount.toNotionRelationFilter(FROM_ACCOUNT))
			}

			if (type != null) add(type.toNotionTagFilter(TYPE))
			if (budget != null) add(budget.toNotionRelationFilter(BUDGET))

			if (date != null) {
				add(date.toNotionDateFilter(DATE))
			} else if (dateFrom != null && dateTo != null) {
				add(buildJsonObject {
					put("and", buildJsonArray {
						add(dateFrom.toNotionDateFilter(DATE, NotionDateFilter.FROM))
						add(dateTo.toNotionDateFilter(DATE, NotionDateFilter.TO))
					})
				})
			} else if (dateFrom != null) {
				add(dateFrom.toNotionDateFilter(DATE, NotionDateFilter.FROM))
			} else if (dateTo != null) {
				add(dateTo.toNotionDateFilter(DATE, NotionDateFilter.TO))
			}

			if (!name.isNullOrEmpty()) {
				add(name.toNotionTextFilter(NAME, NotionTextFilter.CONTAINS))
			}
		}

		val body = buildJsonObject {
			if (filters.isNotEmpty()) {
				put("sorts", buildJsonArray {
					addJsonObject {
						put("property", JsonPrimitive(DATE))
						put("direction", JsonPrimitive("descending"))
					}
				})
				put("page_size", JsonPrimitive(pageSize))
				cursor?.let { put("start_cursor", JsonPrimitive(it)) }

				if (filters.size == 1) put("filter", filters[0])
				else put("filter", buildJsonObject {
					put("and", filters)
				})
			}
		}

		encoder.encodeString(body.toJson())
	}

	override fun deserialize(decoder: Decoder) = TransactionsFilter()
}