package com.adiliqbal.finize.database.model.fake

import com.adiliqbal.finize.database.model.AccountEntity
import com.adiliqbal.finize.database.model.BudgetEntity
import com.adiliqbal.finize.database.model.MoneyEntity
import com.adiliqbal.finize.database.model.ProfileEntity
import com.adiliqbal.finize.database.model.TransactionEntity
import com.adiliqbal.finize.database.model.UserWithProfile
import com.adiliqbal.finize.model.enums.AccountType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

object FakeEntity {

	fun user(id: ID = "userId", name: String = "name") =
		UserWithProfile(id = id, name = name, email = "email", profile = ProfileEntity("currency"))

	fun account(id: ID = "accountId", budget: ID? = null, type: AccountType = AccountType.DEPOSIT) =
		AccountEntity(
			id = id,
			name = "account",
			balance = 50.0,
			budget = budget,
			type = type,
			currency = "currency",
			createdAt = Instant.DISTANT_FUTURE
		)

	fun budget(id: ID = "budgetId") =
		BudgetEntity(id = id, name = "budget", limit = 30.0, createdAt = Instant.DISTANT_FUTURE)

	fun transaction(
		id: ID = "transactionId",
		name: String = "transaction",
		amount: Double = 0.0,
		categories: List<String> = emptyList(),
		accountFrom: ID? = null,
		accountTo: ID? = null,
		budget: ID? = "budgetId",
		date: Instant = Clock.System.now()
	) = TransactionEntity(
		id = id,
		name = name,
		amount = MoneyEntity(amount, "PKR"),
		amountTo = null,
		amountFrom = null,
		amountLocal = MoneyEntity(amount, "PKR"),
		accountTo = accountTo,
		accountFrom = accountFrom,
		budget = budget,
		categories = categories,
		date = date
	)
}