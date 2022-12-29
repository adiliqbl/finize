package com.adiliqbal.finize.network.extensions

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

internal val JsonElement?.isPrimitiveType
	get() = this !is JsonObject && this !is JsonArray && this !is JsonNull

internal fun JsonObject.getString(key: String) = get(key)?.jsonPrimitive?.content