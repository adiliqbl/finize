package com.adiliqbal.finize.network.model.request

import kotlinx.serialization.json.JsonElement

internal interface JsonObjectDelegate {
	fun toJson(): JsonElement?
}