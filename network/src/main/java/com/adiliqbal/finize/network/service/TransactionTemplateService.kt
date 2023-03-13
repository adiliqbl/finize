package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.BaseApiTransaction

interface TransactionTemplateService {
	suspend fun getTemplates(): List<BaseApiTransaction>
	suspend fun createTemplate(template: ApiTransaction): BaseApiTransaction
	suspend fun updateTemplate(template: ApiTransaction)
	suspend fun deleteTemplate(id: ID)
}