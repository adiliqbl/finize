package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.datastore.NotionPreferences
import com.adiliqbal.finize.network.model.request.TransactionsFilter
import javax.inject.Inject

internal class TransactionServiceImpl @Inject constructor(
	private val preferences: NotionPreferences,
	private val notionService: NotionService
) : TransactionService {

	override suspend fun getTransactions(filter: TransactionsFilter?) =
		notionService.getTransactions(preferences.transactionsDB(), filter)
}