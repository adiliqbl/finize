package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class NotionApiUser(
	override val id: ID,
	override val name: String,
	override val email: String? = null,
	@SerialName("avatar_url") override val image: String? = null,
) : BaseApiUser