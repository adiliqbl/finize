package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.datastore.NotionPreferences
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

internal class AccountServiceImpl @Inject constructor(
	private val preferences: NotionPreferences,
	private val notionService: NotionService
) : AccountService {

	override suspend fun getAccounts() = notionService.getAccounts(preferences.accountsDB())

	override suspend fun updateAccount(account: ApiAccount) =
		notionService.updatePage(account.id, account.toJson().decodeJson<JsonObject>()!!)
}