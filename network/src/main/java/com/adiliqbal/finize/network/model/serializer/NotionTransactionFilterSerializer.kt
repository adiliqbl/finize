package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.extensions.NotionDateFilter
import com.adiliqbal.finize.network.extensions.NotionTextFilter
import com.adiliqbal.finize.network.extensions.toNotionDateFilter
import com.adiliqbal.finize.network.extensions.toNotionRelationFilter
import com.adiliqbal.finize.network.extensions.toNotionTextFilter
import com.adiliqbal.finize.network.model.request.TransactionsFilter
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
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
						add(toAccount.toNotionRelationFilter("To Account"))
						add(toAccount.toNotionRelationFilter("From Account"))
					})
				})
			} else if (!toAccount.isNullOrEmpty() && !fromAccount.isNullOrEmpty()) {
				add(buildJsonObject {
					put("and", buildJsonArray {
						add(toAccount.toNotionRelationFilter("To Account"))
						add(fromAccount.toNotionRelationFilter("From Account"))
					})
				})
			} else if (!toAccount.isNullOrEmpty() && fromAccount.isNullOrEmpty()) {
				add(toAccount.toNotionRelationFilter("To Account"))
			} else if (!fromAccount.isNullOrEmpty() && toAccount.isNullOrEmpty()) {
				add(fromAccount.toNotionRelationFilter("From Account"))
			}

			if (date != null) {
				add(date.toNotionDateFilter("Date"))
			} else if (dateFrom != null && dateTo != null) {
				add(buildJsonObject {
					put("and", buildJsonArray {
						add(dateFrom.toNotionDateFilter("Date", NotionDateFilter.FROM))
						add(dateTo.toNotionDateFilter("Date", NotionDateFilter.TO))
					})
				})
			} else if (dateFrom != null) {
				add(dateFrom.toNotionDateFilter("Date", NotionDateFilter.FROM))
			} else if (dateTo != null) {
				add(dateTo.toNotionDateFilter("Date", NotionDateFilter.TO))
			}

			if (!name.isNullOrEmpty()) {
				add(name.toNotionTextFilter("Name", NotionTextFilter.CONTAINS))
			}
		}

		val body = buildJsonObject {
			if (filters.isNotEmpty()) {
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