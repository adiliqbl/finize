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
        if (dateFrom != null || dateTo != null) {
            if (dateFrom == dateTo) {
                whereEqualTo(ApiTransaction.DATE, dateTo!!.toString())
            } else {
                if (dateFrom != null) {
                    whereGreaterThanOrEqualTo(ApiTransaction.DATE, dateFrom!!.toString())
                }
                if (dateTo != null) {
                    whereLessThanOrEqualTo(ApiTransaction.DATE, dateTo!!.toString())
                }
            }
        }

        if (!accountTo.isNullOrEmpty() && accountTo == accountFrom) {
            whereEqualTo(ApiTransaction.ACCOUNT_FROM, accountTo)
            whereEqualTo(ApiTransaction.ACCOUNT_TO, accountTo)
        } else if (!accountTo.isNullOrEmpty() && !accountFrom.isNullOrEmpty()) {
            whereEqualTo(ApiTransaction.ACCOUNT_FROM, accountFrom)
            whereEqualTo(ApiTransaction.ACCOUNT_TO, accountTo)
        } else if (!accountTo.isNullOrEmpty() && accountFrom.isNullOrEmpty()) {
            whereEqualTo(ApiTransaction.ACCOUNT_TO, accountTo)
        } else if (!accountFrom.isNullOrEmpty() && accountTo.isNullOrEmpty()) {
            whereEqualTo(ApiTransaction.ACCOUNT_FROM, accountFrom)
        }

        if (budget != null) whereEqualTo(ApiTransaction.BUDGET, budget)
        if (categories != null)
            categories?.forEach { whereArrayContains(ApiTransaction.CATEGORIES, it) }

        if (!name.isNullOrEmpty()) whereArrayContains(ApiTransaction.KEYWORDS, name!!)
    }
}
