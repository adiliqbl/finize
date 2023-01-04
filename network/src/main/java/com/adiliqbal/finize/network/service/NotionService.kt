package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.request.CreateNotionPageRequest
import com.adiliqbal.finize.network.model.request.ApiTransactionsFilter
import com.adiliqbal.finize.network.model.response.PaginatedList
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.PATCH
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
		@Body body: ApiTransactionsFilter? = null
	): PaginatedList<ApiTransaction>

	@POST(value = "pages")
	suspend fun createPage(@Body body: CreateNotionPageRequest): String

	@PATCH(value = "pages/{id}")
	suspend fun updatePage(@Path("id") id: ID, @Field("properties") body: JsonObject)

	@DELETE(value = "pages/{id}")
	suspend fun deletePage(@Path("id") id: ID)
}