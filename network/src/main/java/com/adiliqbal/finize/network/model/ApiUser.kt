package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.serialization.Serializable

interface BaseApiUser {
	val id: ID
	val name: String
	val email: String?
	val image: String?
	val settings: ApiSettings?
}

@Serializable
data class ApiUser(
	override val id: ID,
	override val name: String,
	override val email: String? = null,
	override val image: String? = null,
	override val settings: ApiSettings? = null
) : BaseApiUser