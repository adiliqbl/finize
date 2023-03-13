package com.adiliqbal.finize.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiPaymentCategory(
	val name: String,
	val priority: Int = 1,
	val hierarchy: List<String>? = null,
)