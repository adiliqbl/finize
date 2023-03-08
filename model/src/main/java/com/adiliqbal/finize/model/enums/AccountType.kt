package com.adiliqbal.finize.model.enums

enum class AccountType(val value: String) {
	INVESTMENT("investment"), DEPOSIT("deposit");
}

fun String?.toAccountType() =
	this?.let { str -> AccountType.values().find { it.value === str } } ?: AccountType.DEPOSIT