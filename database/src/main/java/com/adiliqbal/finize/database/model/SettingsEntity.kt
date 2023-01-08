package com.adiliqbal.finize.database.model

data class SettingsEntity(
	val currency: String,
	val tags: List<String>? = null
)