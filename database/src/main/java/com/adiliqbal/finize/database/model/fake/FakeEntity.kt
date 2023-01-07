package com.adiliqbal.finize.database.model.fake

import com.adiliqbal.finize.database.model.AccountEntity
import com.adiliqbal.finize.database.model.BudgetEntity
import com.adiliqbal.finize.database.model.TransactionEntity
import com.adiliqbal.finize.database.model.UserEntity
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate

object FakeEntity {

	fun user(id: ID = "userId", name: String = "name") = UserEntity(id = id, name = name)

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
		date: LocalDate = LocalDate(2022, 8, 8)
	) = TransactionEntity(
		id = id,
		name = name,
		toAccount = accountTo,
		fromAccount = accountFrom,
		budget = budget,
		type = type,
		date = date.toEpochDays()
	)
}