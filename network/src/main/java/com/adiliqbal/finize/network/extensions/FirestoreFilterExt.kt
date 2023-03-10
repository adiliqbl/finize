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
		if (date != null) {
			whereEqualTo(ApiTransaction.DATE, date!!.toString())
		} else if (dateFrom != null || dateTo != null) {
			if (dateFrom != null) {
				whereGreaterThanOrEqualTo(ApiTransaction.DATE, dateFrom!!.toString())
			}
			if (dateTo != null) {
				whereLessThanOrEqualTo(ApiTransaction.DATE, dateTo!!.toString())
			}
		}

		if (!accountTo.isNullOrEmpty() && accountTo == accountFrom) {
			whereEqualTo(ApiTransaction.FROM_ACCOUNT, accountTo)
			whereEqualTo(ApiTransaction.TO_ACCOUNT, accountTo)
		} else if (!accountTo.isNullOrEmpty() && !accountFrom.isNullOrEmpty()) {
			whereEqualTo(ApiTransaction.FROM_ACCOUNT, accountFrom)
			whereEqualTo(ApiTransaction.TO_ACCOUNT, accountTo)
		} else if (!accountTo.isNullOrEmpty() && accountFrom.isNullOrEmpty()) {
			whereEqualTo(ApiTransaction.TO_ACCOUNT, accountTo)
		} else if (!accountFrom.isNullOrEmpty() && accountTo.isNullOrEmpty()) {
			whereEqualTo(ApiTransaction.FROM_ACCOUNT, accountFrom)
		}

		if (budget != null) whereEqualTo(ApiTransaction.BUDGET, budget)
		if (category != null) category?.forEach { whereArrayContains(ApiTransaction.CATEGORY, it) }

		if (!name.isNullOrEmpty()) whereArrayContains(ApiTransaction.KEYWORDS, name!!)
	}
}