package com.adiliqbal.finize.database.model

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.serialization.Serializable

@Serializable
data class TagEntity(
	val id: ID,
	val name: String,
	val color: String? = null
)