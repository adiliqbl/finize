package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.request.TransactionsFilter
import com.adiliqbal.finize.network.model.response.PaginatedList
import retrofit2.http.Body

interface TransactionService {

	suspend fun getTransactions(@Body filter: TransactionsFilter? = null): PaginatedList<ApiTransaction>
}