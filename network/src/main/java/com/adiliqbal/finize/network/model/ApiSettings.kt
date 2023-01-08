package com.adiliqbal.finize.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiSettings(
	val currency: String
)