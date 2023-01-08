package com.adiliqbal.finize.network.service.notion

import com.adiliqbal.finize.common.util.NotSupportedException
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.service.TransactionTemplateService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NotionTransactionTemplateService @Inject constructor() : TransactionTemplateService {

	override suspend fun getTemplates() = throw NotSupportedException()

	override suspend fun createTemplate(template: ApiTransaction) = throw NotSupportedException()

	override suspend fun updateTemplate(template: ApiTransaction) = throw NotSupportedException()

	override suspend fun deleteTemplate(id: ID) = throw NotSupportedException()
}