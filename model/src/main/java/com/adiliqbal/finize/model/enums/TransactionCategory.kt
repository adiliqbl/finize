package com.adiliqbal.finize.model.enums

enum class TransactionCategory {
	Misc,

	Income,
	Deposit,
	Transfer,
	ATM,
	Withdraw,

	Payment,
	Service,
	Refund,
	Loan,
	Rent,
	Installment,
	Bill,

	BankFees,
	Utilities,
	Automotive,
	Fuel,
	Travel,
	Booking,
	Hotel,
	Transportation,
	Construction,
	Subscription,

	Community,
	Education,
	Sports,
	Healthcare,
	Recreation,
	Entertainment,
	Grocery,
	Cosmetics,
	FoodAndDrink,
	ClothingAndAccessories,
	ComputersAndElectronics,
	Stationery,
	Decoration,
}

fun String?.toTransactionCategory() =
	this?.let { TransactionCategory.valueOf(this) } ?: TransactionCategory.Misc