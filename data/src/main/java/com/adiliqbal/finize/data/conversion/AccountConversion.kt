package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.common.util.CurrencyUtil
import com.adiliqbal.finize.database.model.AccountEntity
import com.adiliqbal.finize.database.model.AccountWithRefs
import com.adiliqbal.finize.model.Account
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.BaseApiAccount

internal fun Account.toApi() = ApiAccount(
	id = id,
	name = name,
	type = type,
	balance = balance,
	currency = currency.currencyCode,
	budget = budget?.id,
	createdAt = createdAt
)

internal fun BaseApiAccount.toEntity() = AccountEntity(
	id, name, type, balance, currency, budget, createdAt
)

internal fun AccountWithRefs.toModel() = Account(
	id = account.id,
	name = account.name,
	balance = account.balance,
	type = account.type,
	currency = CurrencyUtil.of(account.currency),
	budget = budget?.toModel(),
	createdAt = account.createdAt
)

internal fun AccountEntity.toModel() = Account(
	id = id,
	name = name,
	balance = balance,
	type = type,
	currency = CurrencyUtil.of(currency),
	createdAt = createdAt
)