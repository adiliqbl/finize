package com.adiliqbal.finize.network.extensions

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Suppress("UNCHECKED_CAST")
internal fun JsonObject.parseNotionString(key: String): String? {
    return try {
        val value = get(key)
        if (value.isPrimitiveType) return value?.jsonPrimitive?.contentOrNull

        val obj = value as JsonObject
        val type = obj.getString("type")

        if (obj.containsKey("content")) return obj["content"]!!.jsonPrimitive.content
        else if (obj.containsKey("string")) return obj["string"]!!.jsonPrimitive.content
        else if (obj.containsKey("type")) {
            when (type) {
                "text" -> return obj["text"]!!.jsonObject.parseNotionString("content")
                "file" -> return parseNotionFileUrl("file")
                "formula" -> return obj["formula"]!!.jsonObject.parseNotionString("string")
                "rollup" -> return obj["rollup"]!!.jsonObject.parseNotionString("string")
                "title",
                "rich_text" -> {
                    val props =
                        if (type == "title") obj["title"]!!.jsonArray else obj["rich_text"]!!.jsonArray

                    for (prop in props) {
                        val text = prop.jsonObject.parseNotionString("text")
                        if (text != null) return text
                    }
                }
            }
        }
        null
    } catch (_: Exception) {
        null
    }
}

@Suppress("UNCHECKED_CAST")
internal fun JsonObject.parseNotionDouble(key: String): Double {
    return try {
        val value = get(key)
        if (value.isPrimitiveType) return (value!!.jsonPrimitive.contentOrNull!!).toDouble()

        val obj = value as JsonObject
        if (obj.containsKey("number")) return obj["number"]!!.jsonPrimitive.content.toDouble()
        else if (obj.containsKey("type")) {
            if (obj.getString("type") == "rollup")
                return obj["rollup"]!!.jsonObject.parseNotionDouble("number")
            else if (obj.getString("type") == "formula")
                return obj["formula"]!!.jsonObject.parseNotionDouble("number")
        }
        0.0
    } catch (_: Exception) {
        0.0
    }
}

@Suppress("UNCHECKED_CAST")
internal fun JsonObject.parseNotionDate(key: String): Instant? {
    return try {
        val value = get(key)
        if (value.isPrimitiveType) return DateUtil.parseDate(value!!.jsonPrimitive.contentOrNull!!)

        val obj = value as JsonObject
        if (obj.containsKey("start")) return DateUtil.parseDate(obj["start"]!!.jsonPrimitive.content)
        else if (obj.containsKey("type")) {
            if (obj.getString("type") == "date") return obj["date"]!!.jsonObject.parseNotionDate("start")
            else if (obj.getString("type") == "formula")
                return obj["formula"]!!.jsonObject.parseNotionDate("start")
        }
        null
    } catch (_: Exception) {
        null
    }
}

@Suppress("UNCHECKED_CAST")
internal fun JsonObject.parseNotionDateTime(key: String): Instant? {
    return try {
        val value = get(key)
        if (value.isPrimitiveType) return Instant.parse(value!!.jsonPrimitive.contentOrNull!!)

        val obj = value as JsonObject
        if (obj.containsKey("created_time"))
            return Instant.parse(obj["created_time"]!!.jsonPrimitive.content)
        else if (obj.containsKey("type")) {
            if (obj.getString("type") == "created_time")
                return obj["created_time"]!!.jsonObject.parseNotionDateTime("created_time")
            else if (obj.getString("type") == "date_time")
                return obj["date_time"]!!.jsonObject.parseNotionDateTime("date_time")
            else if (obj.getString("type") == "formula")
                return obj["formula"]!!.jsonObject.parseNotionDateTime("created_time")
        }
        null
    } catch (_: Exception) {
        null
    }
}

@Suppress("UNCHECKED_CAST")
internal fun JsonObject.parseNotionRelation(key: String): List<ID>? {
    return try {
        val obj = get(key) as JsonObject
        if (obj.getString("type") == "relation") {
            return obj["relation"]?.jsonArray?.map { it.jsonObject.getString("id") as ID }
        } else null
    } catch (_: Exception) {
        null
    }
}

@Suppress("UNCHECKED_CAST")
internal fun JsonObject.parseNotionMultiselect(key: String): List<String>? {
    return try {
        val value = get(key)
        if (value is JsonArray) {
            return value.mapNotNull { if (it is JsonObject) it.toTag() else null }
        }

        val obj = value as JsonObject
        if (obj.getString("type") == "multi_select") {
            obj["multi_select"]!!.jsonArray.let { array ->
                return array.mapNotNull { if (it is JsonObject) it.toTag() else null }
            }
        }
        null
    } catch (_: Exception) {
        null
    }
}

@Suppress("UNCHECKED_CAST")
internal fun JsonObject.parseNotionSelect(key: String): String? {
    return try {
        val obj = get(key)?.jsonObject as JsonObject
        if (obj.getString("type") == "select") {
            return obj["select"]!!.jsonObject.toTag()
        } else {
            return toTag()
        }
    } catch (_: Exception) {
        null
    }
}

@Suppress("UNCHECKED_CAST")
private fun JsonObject.parseNotionFileUrl(key: String): String? {
    return try {
        val value = get(key)
        if (value.isPrimitiveType) return value?.jsonPrimitive?.contentOrNull

        val obj = value as JsonObject
        if (obj.containsKey("url")) return obj.getString("url")
        else if (obj.containsKey("type")) {
            if (obj.getString("type") == "file") return obj["file"]!!.jsonObject.parseNotionFileUrl("URL")
        }
        null
    } catch (_: Exception) {
        null
    }
}

private fun JsonObject.toTag() = getString("name")
