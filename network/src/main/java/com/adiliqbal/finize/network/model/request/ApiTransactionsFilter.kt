package com.adiliqbal.finize.network.model.request

import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.extensions.NotionDateFilter
import com.adiliqbal.finize.network.extensions.NotionTextFilter
import com.adiliqbal.finize.network.extensions.toNotionDateFilter
import com.adiliqbal.finize.network.extensions.toNotionRelationFilter
import com.adiliqbal.finize.network.extensions.toNotionTagFilter
import com.adiliqbal.finize.network.extensions.toNotionTextFilter
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

internal fun TransactionsFilter.toJson(): JsonElement? {
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

		if (type != null) add(type!!.toNotionTagFilter(NotionTransactionSerializer.TYPE))
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