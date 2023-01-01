package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
	val id: ID,
	val name: String = "",
	@SerialName("avatar_url") val image: String? = null,
)