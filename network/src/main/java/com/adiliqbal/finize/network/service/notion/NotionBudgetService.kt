package com.adiliqbal.finize.network.service.notion

import com.adiliqbal.finize.datastore.NotionPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.BaseApiBudget
import com.adiliqbal.finize.network.model.NotionApiBudget
import com.adiliqbal.finize.network.model.request.CreateNotionPageRequest
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.service.BudgetService
import com.adiliqbal.finize.network.source.NotionService
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject

@Singleton
internal class NotionBudgetService
@Inject
constructor(
    private val notionPreferences: NotionPreferences,
    private val notionService: NotionService
) : BudgetService {

    override suspend fun getBudgets() =
        notionService.getBudgets(notionPreferences.budgetsDB()) as PaginatedList<BaseApiBudget>

    override suspend fun createBudget(budget: ApiBudget): NotionApiBudget {
        return notionService
            .createPage(
                CreateNotionPageRequest(
                    notionPreferences.budgetsDB(),
                    NotionApiBudget(budget).toJson().decodeJson<JsonObject>()!!
                )
            )
            .decodeJson<NotionApiBudget>()!!
    }

    override suspend fun updateBudget(budget: ApiBudget) =
        notionService.updatePage(budget.id, budget.toJson().decodeJson<JsonObject>()!!)

    override suspend fun deleteBudget(id: ID) = notionService.deletePage(id)
}
