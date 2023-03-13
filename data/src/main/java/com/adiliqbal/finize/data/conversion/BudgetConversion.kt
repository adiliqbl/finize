package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.database.model.BudgetEntity
import com.adiliqbal.finize.model.Budget
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.BaseApiBudget

internal fun Budget.toApi() = ApiBudget(
	id, name, limit, expireAt, createdAt
)

internal fun BaseApiBudget.toEntity() = BudgetEntity(
	id, name, limit, expireAt, createdAt
)

internal fun BudgetEntity.toModel() = Budget(
	id, name, limit, expireAt, createdAt
)