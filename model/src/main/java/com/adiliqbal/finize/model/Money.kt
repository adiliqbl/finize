package com.adiliqbal.finize.model

import java.util.Currency

data class Money(
	val amount: BigDecimal,
	val currency: Currency
)