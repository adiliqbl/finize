package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.serialization.Serializable

@Serializable
data class ApiTag(
	val id: ID,
	val name: String,
	val color: String? = null
)