package com.adiliqbal.finize.model

import java.util.Currency
import java.util.Locale

data class Profile(
	val currency: Currency = Currency.getInstance(Locale.getDefault())
)