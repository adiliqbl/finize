package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.request.TransactionsFilter
import com.adiliqbal.finize.network.model.response.PaginatedList

interface TransactionService {

	suspend fun getTransactions(filter: TransactionsFilter? = null): PaginatedList<ApiTransaction>

	suspend fun createTransaction(transaction: ApiTransaction): ApiTransaction

	suspend fun updateTransaction(transaction: ApiTransaction)

	suspend fun deleteTransaction(id: ID)
}