package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.request.TransactionsFilter
import com.adiliqbal.finize.network.model.response.PaginatedList
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

internal interface NotionService {

	@POST(value = "databases/{db}/query")
	suspend fun getAccounts(@Path("db") db: String): PaginatedList<ApiAccount>

	@POST(value = "databases/{db}/query")
	suspend fun getBudgets(@Path("db") db: String): PaginatedList<ApiBudget>

	@POST(value = "databases/{db}/query")
	suspend fun getTransactions(
		@Path("db") db: String,
		@Body filter: TransactionsFilter? = null
	): PaginatedList<ApiTransaction>
}