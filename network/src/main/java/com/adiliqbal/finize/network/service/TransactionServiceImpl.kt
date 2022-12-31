package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.datastore.NotionPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.request.CreateNotionPageRequest
import com.adiliqbal.finize.network.model.request.TransactionsFilter
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

internal class TransactionServiceImpl @Inject constructor(
	private val preferences: NotionPreferences,
	private val notionService: NotionService
) : TransactionService {

	override suspend fun getTransactions(filter: TransactionsFilter?) =
		notionService.getTransactions(preferences.transactionsDB(), filter)

	override suspend fun createTransaction(transaction: ApiTransaction): ApiTransaction {
		return notionService.createPage(
			CreateNotionPageRequest(
				preferences.transactionsDB(),
				transaction.toJson().decodeJson<JsonObject>()!!
			)
		).decodeJson<ApiTransaction>()!!
	}

	override suspend fun updateTransaction(transaction: ApiTransaction) =
		notionService.updatePage(transaction.id, transaction.toJson().decodeJson<JsonObject>()!!)

	override suspend fun deleteTransaction(id: ID) = notionService.deletePage(id)
}