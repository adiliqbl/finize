package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.database.model.BudgetEntity
import com.adiliqbal.finize.model.Budget
import com.adiliqbal.finize.network.model.ApiBudget

internal fun ApiBudget.toEntity() = BudgetEntity(
	id, name, limit, expireAt, createdAt
)

internal fun BudgetEntity.toModel() = Budget(
	id, name, limit, expireAt, createdAt
)