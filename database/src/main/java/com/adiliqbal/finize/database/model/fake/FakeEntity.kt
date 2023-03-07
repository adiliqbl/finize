package com.adiliqbal.finize.database.model.fake

import com.adiliqbal.finize.database.model.AccountEntity
import com.adiliqbal.finize.database.model.BudgetEntity
import com.adiliqbal.finize.database.model.SettingsEntity
import com.adiliqbal.finize.database.model.TransactionEntity
import com.adiliqbal.finize.database.model.UserWithSettingsEntity
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

object FakeEntity {

	fun user(id: ID = "userId", name: String = "name") =
		UserWithSettingsEntity(id = id, name = name, settings = SettingsEntity("currency"))

	fun account(id: ID = "accountId") =
		AccountEntity(id = id, name = "account", startingBalance = 10.0, currentBalance = 50.0)

	fun budget(id: ID = "budgetId") =
		BudgetEntity(id = id, name = "budget", maximum = 30.0)

	fun transaction(
		id: ID = "transactionId",
		name: String = "transaction",
		accountFrom: ID? = null,
		accountTo: ID? = null,
		budget: ID? = "budgetId",
		type: TransactionType = TransactionType.UNKNOWN,
		date: Instant = Clock.System.now()
	) = TransactionEntity(
		id = id,
		name = name,
		accountTo = accountTo,
		accountFrom = accountFrom,
		budget = budget,
		type = type,
		date = date.toEpochMilliseconds()
	)
}