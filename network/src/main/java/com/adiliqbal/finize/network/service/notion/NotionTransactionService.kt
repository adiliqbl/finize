package com.adiliqbal.finize.network.service.notion

import com.adiliqbal.finize.datastore.NotionPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.NotionApiTransaction
import com.adiliqbal.finize.network.model.request.CreateNotionPageRequest
import com.adiliqbal.finize.network.model.request.PaginationQuery
import com.adiliqbal.finize.network.service.TransactionService
import com.adiliqbal.finize.network.source.NotionService
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NotionTransactionService @Inject constructor(
	private val notionPreferences: NotionPreferences,
	private val notionService: NotionService
) : TransactionService {

	override suspend fun getTransactions(paging: PaginationQuery, filter: TransactionsFilter?) = TODO("")
//		notionService.getTransactions(
//			notionPreferences.transactionsDB(),
//			NotionDatabaseQuery(paging.copy(filter = filter?.toNotionFilter()))
//		) as PaginatedList<BaseApiTransaction>

	override suspend fun createTransaction(transaction: ApiTransaction): NotionApiTransaction {
		return notionService.createPage(
			CreateNotionPageRequest(
				notionPreferences.transactionsDB(),
				NotionApiTransaction(transaction).toJson().decodeJson<JsonObject>()!!
			)
		).decodeJson<NotionApiTransaction>()!!
	}

	override suspend fun updateTransaction(transaction: ApiTransaction) =
		notionService.updatePage(
			transaction.id,
			NotionApiTransaction(transaction).toJson().decodeJson<JsonObject>()!!
		)

	override suspend fun deleteTransaction(id: ID) = notionService.deletePage(id)
}