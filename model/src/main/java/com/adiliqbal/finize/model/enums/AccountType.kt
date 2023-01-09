package com.adiliqbal.finize.model.enums

enum class AccountType(val value: String) {
	INVESTMENT("investment"),
	DEPOSIT("deposit"),
	SAVING("saving");
}

fun String?.toAccountType() = this?.let { AccountType.valueOf(it) } ?: AccountType.DEPOSIT