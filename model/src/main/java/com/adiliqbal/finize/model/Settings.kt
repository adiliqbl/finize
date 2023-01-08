package com.adiliqbal.finize.model

import java.util.*

data class Settings(
	val currency: Currency = Currency.getInstance(Locale.getDefault()),
	val tags: List<String> = emptyList()
)