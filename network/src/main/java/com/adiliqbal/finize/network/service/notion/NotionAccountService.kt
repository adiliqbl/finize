package com.adiliqbal.finize.network.service.notion

import com.adiliqbal.finize.datastore.NotionPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.BaseApiAccount
import com.adiliqbal.finize.network.model.NotionApiAccount
import com.adiliqbal.finize.network.model.request.CreateNotionPageRequest
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.service.AccountService
import com.adiliqbal.finize.network.source.NotionService
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject

@Singleton
internal class NotionAccountService @Inject constructor(
    private val notionPreferences: NotionPreferences,
    private val notionService: NotionService
) : AccountService {

    override suspend fun getAccounts() =
        notionService.getAccounts(notionPreferences.accountsDB()) as PaginatedList<BaseApiAccount>

    override suspend fun createAccount(account: ApiAccount): NotionApiAccount {
        return notionService
            .createPage(
                CreateNotionPageRequest(
                    notionPreferences.accountsDB(),
                    NotionApiAccount(account).toJson().decodeJson<JsonObject>()!!
                )
            )
            .decodeJson<NotionApiAccount>()!!
    }

    override suspend fun updateAccount(account: ApiAccount) =
        notionService.updatePage(
            account.id, NotionApiAccount(account).toJson().decodeJson<JsonObject>()!!
        )

    override suspend fun deleteAccount(id: ID) = notionService.deletePage(id)
}
