package com.adiliqbal.finize.model

import kotlinx.datetime.Instant

data class ExchangeRate(
	val rate: Float,
	val date: Instant
)