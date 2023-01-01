package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID

data class Tag(
	val id: ID,
	val name: String,
	val color: String? = null
)