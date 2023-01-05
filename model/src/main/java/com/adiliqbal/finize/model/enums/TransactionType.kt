package com.adiliqbal.finize.model.enums

enum class TransactionType(val value: String) {
	EXPENSE("Expense"),
	INCOME("Income"),
	DEPOSIT("Deposit"),
	TRANSFER("Transfer"),
	SAVING("Saving"),
	LOAN("Loan"),
	BORROW("Borrow"),
	UNKNOWN("")
}

fun String?.toTransactionType() =
	this?.let { TransactionType.valueOf(it) } ?: TransactionType.UNKNOWN