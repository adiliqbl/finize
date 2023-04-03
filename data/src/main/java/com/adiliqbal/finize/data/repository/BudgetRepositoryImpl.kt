package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.extensions.channelFlowWithAwait
import com.adiliqbal.finize.common.extensions.withScope
import com.adiliqbal.finize.common.network.Dispatcher
import com.adiliqbal.finize.common.network.Thread
import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.data.extensions.launchSafeApi
import com.adiliqbal.finize.data.extensions.withExceptions
import com.adiliqbal.finize.database.dao.BudgetDao
import com.adiliqbal.finize.model.Budget
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.service.BudgetService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class BudgetRepositoryImpl @Inject constructor(
    private val budgetDao: BudgetDao,
    private val budgetService: BudgetService,
    @Dispatcher(Thread.IO) private val ioDispatcher: CoroutineDispatcher,
) : BudgetRepository {

    override fun getBudgets(search: String?) = channelFlowWithAwait {
        withScope(ioDispatcher) {
            budgetDao.getAll().map {
                it.mapNotNull { entity ->
                    if (search.isNullOrEmpty() || entity.name.contains(search)) entity.toModel()
                    else null
                }
            }.collect { trySend(it) }
        }
        launchSafeApi(ioDispatcher) {
            budgetService.getBudgets().let {
                it.data?.map { budget -> budget.toEntity() }
                    ?.let { budgets ->
                        budgetDao.transaction {
                            budgetDao.clear()
                            budgetDao.upsert(budgets)
                        }
                    }
            }
        }
    }

    override fun getBudget(id: ID): Flow<Budget> =
        budgetDao.get(id).withExceptions().map { it.toModel() }

    override suspend fun createBudget(budget: Budget): Budget {
        return budgetService.createBudget(budget.toApi()).toEntity().let {
            budgetDao.upsert(it)
            it.toModel()
        }
    }

    override suspend fun updateBudget(budget: Budget) {
        return budget.toApi().let {
            budgetService.updateBudget(it)
            val entity = it.toEntity()
            budgetDao.upsert(entity)
        }
    }

    override suspend fun deleteBudget(id: ID) {
        budgetService.deleteBudget(id)
        budgetDao.delete(id)
    }
}
