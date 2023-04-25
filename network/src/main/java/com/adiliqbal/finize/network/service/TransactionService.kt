package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.model.enums.SortOrder.DESCENDING
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.BaseApiTransaction
import com.adiliqbal.finize.network.model.request.PaginationQuery
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer.DATE

interface TransactionService {
	suspend fun getTransactions(
		paging: PaginationQuery = PaginationQuery(sortField = DATE, sortOrder = DESCENDING),
		filter: TransactionsFilter? = null
	): PaginatedList<BaseApiTransaction>

	suspend fun getTransaction(id: ID): BaseApiTransaction
	suspend fun createTransaction(transaction: ApiTransaction): BaseApiTransaction
	suspend fun updateTransaction(transaction: ApiTransaction)
	suspend fun deleteTransaction(id: ID)
}
