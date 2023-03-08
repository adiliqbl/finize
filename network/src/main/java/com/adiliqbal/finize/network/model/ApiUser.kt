package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.CurrencyUtil
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BaseApiUser {
	val id: ID
	val name: String
	val email: String
	val image: String?
	val profile: ApiProfile
}

@Serializable
data class ApiUser(
	override val id: ID,
	override val name: String,
	override val email: String,
	override val image: String? = null,
	override val profile: ApiProfile
) : BaseApiUser

@Serializable
internal class NotionApiUser(
	override val id: ID,
	override val name: String,
	override val email: String,
	@SerialName("avatar_url") override val image: String? = null,
	override val profile: ApiProfile = ApiProfile(currency = CurrencyUtil.default.currencyCode)
) : BaseApiUser