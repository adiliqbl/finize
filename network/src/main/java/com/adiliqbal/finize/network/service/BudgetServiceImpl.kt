package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.datastore.NotionPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject

internal class BudgetServiceImpl @Inject constructor(
	private val preferences: NotionPreferences,
	private val notionService: NotionService
) : BudgetService {

	override suspend fun getBudgets() = notionService.getBudgets(preferences.budgetsDB())

	override suspend fun updateBudget(budget: ApiBudget) =
		notionService.updatePage(budget.id, budget.toJson().decodeJson<JsonObject>()!!)
}