package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.BaseApiBudget
import com.adiliqbal.finize.network.model.response.PaginatedList

interface BudgetService {
	suspend fun getBudgets(): PaginatedList<BaseApiBudget>
	suspend fun createBudget(budget: ApiBudget): BaseApiBudget
	suspend fun updateBudget(budget: ApiBudget)
	suspend fun deleteBudget(id: ID)
}