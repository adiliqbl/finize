package com.adiliqbal.finize.network.model.request

import com.adiliqbal.finize.model.extensions.ID
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

data class CreateNotionPageRequest(private val databaseID: ID, private val properties: JsonObject) {
    fun toJson() = buildJsonObject {
        put(
            "parent",
            buildJsonObject {
                put("type", JsonPrimitive("database_id"))
                put("database_id", JsonPrimitive(databaseID))
            }
        )
        put("properties", properties)
    }
}
