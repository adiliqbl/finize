package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.model.Budget
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
	fun getBudgets(search: String? = null): Flow<List<Budget>>
	fun getBudget(id: ID): Flow<Budget>
	suspend fun createBudget(budget: Budget): Budget
	suspend fun updateBudget(budget: Budget)
	suspend fun deleteBudget(id: ID)
}
