package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.database.dao.BudgetDao
import com.adiliqbal.finize.network.service.BudgetService
import javax.inject.Inject

internal class BudgetRepositoryImpl @Inject constructor(
	private val budgetDao: BudgetDao,
	private val budgetService: BudgetService
) : BudgetRepository