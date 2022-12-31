package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.response.PaginatedList

interface BudgetService {

	suspend fun getBudgets(): PaginatedList<ApiBudget>

	suspend fun updateBudget(budget: ApiBudget)
}