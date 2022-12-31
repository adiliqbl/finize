package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.datastore.NotionPreferences
import javax.inject.Inject

internal class BudgetServiceImpl @Inject constructor(
	private val preferences: NotionPreferences,
	private val notionService: NotionService
) : BudgetService {

	override suspend fun getBudgets() = notionService.getBudgets(preferences.budgetsDB())
}