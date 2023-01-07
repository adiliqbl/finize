package com.adiliqbal.finize.model.fake

import com.adiliqbal.finize.model.Account
import com.adiliqbal.finize.model.Budget
import com.adiliqbal.finize.model.Transaction
import com.adiliqbal.finize.model.User
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate

object FakeModel {

	fun user(id: ID = "userId") = User(id = id, name = "name")

	fun account(id: ID = "accountId") =
		Account(id = id, name = "account", startingBalance = 10.0, currentBalance = 50.0)

	fun budget(id: ID = "budgetId") =
		Budget(id = id, name = "budget", maximum = 30.0)

	fun transaction(
		id: ID = "transactionId",
		accountFrom: ID? = "accountFromId",
		accountTo: ID? = null,
		budget: ID = "budgetId",
		type: TransactionType = TransactionType.UNKNOWN
	) = Transaction(
		id = id,
		name = "transaction",
		toAccount = accountFrom,
		fromAccount = accountTo,
		budget = budget,
		type = type,
		date = LocalDate.parse("2022-08-08")
	)
}