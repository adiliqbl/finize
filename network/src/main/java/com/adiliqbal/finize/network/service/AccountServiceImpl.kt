package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.datastore.NotionPreferences
import javax.inject.Inject

internal class AccountServiceImpl @Inject constructor(
	private val preferences: NotionPreferences,
	private val notionService: NotionService
) : AccountService {

	override suspend fun getAccounts() = notionService.getAccounts(preferences.accountsDB())
}