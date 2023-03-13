package com.adiliqbal.finize.model.enums

enum class AccountType {
	INVESTMENT,
	DEPOSIT;
}

fun String?.toAccountType() = this?.let { AccountType.valueOf(it) } ?: AccountType.DEPOSIT