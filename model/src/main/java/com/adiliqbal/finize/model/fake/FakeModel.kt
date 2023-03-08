package com.adiliqbal.finize.model.fake

import com.adiliqbal.finize.model.Account
import com.adiliqbal.finize.model.Budget
import com.adiliqbal.finize.model.Money
import com.adiliqbal.finize.model.Profile
import com.adiliqbal.finize.model.Transaction
import com.adiliqbal.finize.model.User
import com.adiliqbal.finize.model.enums.AccountType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Clock
import java.util.*

object FakeModel {

	fun user(id: ID = "userId") = User(
		id = id,
		name = "name",
		email = "user@test.com",
		profile = Profile(currency = Currency.getInstance("PKR"))
	)

	fun account(id: ID = "accountId", type: AccountType = AccountType.DEPOSIT) = Account(
		id = id,
		name = "account",
		balance = 50.0,
		currency = Currency.getInstance("PKR"),
		type = type
	)

	fun budget(id: ID = "budgetId") = Budget(id = id, name = "budget", limit = 30.0)

	fun transaction(
		id: ID = "transactionId",
		accountFrom: ID? = "accountFromId",
		accountTo: ID? = null,
		budget: ID = "budgetId",
		amount: Double = 0.0,
		categories: List<String> = emptyList()
	) = Transaction(
		id = id,
		name = "transaction",
		accountTo = accountFrom,
		accountFrom = accountTo,
		budget = budget,
		categories = categories,
		amount = Money(amount = amount, currency = Currency.getInstance("PKR")),
		amountTo = Money(amount = amount, currency = Currency.getInstance("PKR")),
		amountFrom = Money(amount = amount, currency = Currency.getInstance("PKR")),
		amountLocal = Money(amount = amount, currency = Currency.getInstance("PKR")),
		date = Clock.System.now()
	)
}