package com.adiliqbal.finize.network.source

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.NotionApiAccount
import com.adiliqbal.finize.network.model.NotionApiBudget
import com.adiliqbal.finize.network.model.NotionApiTransaction
import com.adiliqbal.finize.network.model.request.CreateNotionPageRequest
import com.adiliqbal.finize.network.model.request.NotionDatabaseQuery
import com.adiliqbal.finize.network.model.response.PaginatedList
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

internal interface NotionService {

    @POST(value = "/databases/{db}/query")
    suspend fun getAccounts(@Path("db") db: String): PaginatedList<NotionApiAccount>

    @POST(value = "/databases/{db}/query")
    suspend fun getBudgets(@Path("db") db: String): PaginatedList<NotionApiBudget>

    @POST(value = "/databases/{db}/query")
    suspend fun getTransactions(
        @Path("db") db: String,
        @Body body: NotionDatabaseQuery? = null
    ): PaginatedList<NotionApiTransaction>

    @POST(value = "/pages")
    suspend fun createPage(@Body body: CreateNotionPageRequest): String

    @GET(value = "/pages/{id}")
    suspend fun getPage(@Path("id") id: ID): String

    @PATCH(value = "/pages/{id}")
    suspend fun updatePage(@Path("id") id: ID, @Field("properties") body: JsonObject)

    @DELETE(value = "/pages/{id}")
    suspend fun deletePage(@Path("id") id: ID)
}
