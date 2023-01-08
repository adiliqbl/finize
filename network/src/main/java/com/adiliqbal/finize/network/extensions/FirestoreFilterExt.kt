package com.adiliqbal.finize.network.extensions

import com.adiliqbal.finize.model.enums.SortOrder
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.request.PaginationQuery
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query.Direction
import kotlinx.serialization.json.JsonObject

internal fun CollectionReference.paginate(query: PaginationQuery) = apply {
	query.sortField?.let {
		orderBy(
			it,
			if (query.sortOrder == SortOrder.DESCENDING) Direction.DESCENDING else Direction.ASCENDING
		)
	}

	if (query.filter is JsonObject) {
		query.filter.mapKeys { whereEqualTo(it.key, it.value) }
	}

	val startIndex = ((query.page - 1) * query.pageSize)
	startAt(startIndex)
	limit(query.pageSize.toLong())
}

internal fun CollectionReference.filter(filter: TransactionsFilter?) = apply {
	filter?.run {
		if (!toAccount.isNullOrEmpty() && toAccount == fromAccount) {
			whereEqualTo(ApiTransaction.FROM_ACCOUNT, toAccount)
			whereEqualTo(ApiTransaction.TO_ACCOUNT, toAccount)
		} else if (!toAccount.isNullOrEmpty() && !fromAccount.isNullOrEmpty()) {
			whereEqualTo(ApiTransaction.FROM_ACCOUNT, fromAccount)
			whereEqualTo(ApiTransaction.TO_ACCOUNT, toAccount)
		} else if (!toAccount.isNullOrEmpty() && fromAccount.isNullOrEmpty()) {
			whereEqualTo(ApiTransaction.TO_ACCOUNT, toAccount)
		} else if (!fromAccount.isNullOrEmpty() && toAccount.isNullOrEmpty()) {
			whereEqualTo(ApiTransaction.FROM_ACCOUNT, fromAccount)
		}

		if (type != null) whereEqualTo(ApiTransaction.TYPE, type!!.value)
		if (budget != null) whereEqualTo(ApiTransaction.BUDGET, budget)

		if (date != null) {
			whereEqualTo(ApiTransaction.DATE, date.toString())
		} else if (dateFrom != null || dateTo != null) {
			if (dateFrom != null) {
				whereGreaterThanOrEqualTo(ApiTransaction.DATE, dateFrom!!.toEpochDays())
			}
			if (dateTo != null) {
				whereLessThanOrEqualTo(ApiTransaction.DATE, dateTo!!.toEpochDays())
			}
		}

		if (!name.isNullOrEmpty()) whereArrayContains(ApiTransaction.KEYWORDS, name!!)
		if (!tags.isNullOrEmpty()) whereArrayContainsAny(ApiTransaction.TAGS, tags!!)
	}
}