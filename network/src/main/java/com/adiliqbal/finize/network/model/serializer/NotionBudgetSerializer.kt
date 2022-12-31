package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDateTime
import com.adiliqbal.finize.network.extensions.parseNotionDouble
import com.adiliqbal.finize.network.extensions.parseNotionString
import com.adiliqbal.finize.network.extensions.toNotionNumber
import com.adiliqbal.finize.network.extensions.toNotionTitle
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject

internal object NotionBudgetSerializer : KSerializer<ApiBudget> {

	private const val ID = "id"
	private const val NAME = "Name"
	private const val SPENT = "Spent"
	private const val MAXIMUM = "Maximum"
	private const val CREATED_TIME = "created_time"

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("NotionBudget", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: ApiBudget) {
		val body = buildJsonObject {
			put(NAME, value.name.toNotionTitle())
			put(SPENT, value.spent.toNotionNumber())
			put(MAXIMUM, value.maximum.toNotionNumber())
		}

		encoder.encodeString(body.toJson())
	}

	override fun deserialize(decoder: Decoder): ApiBudget {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return ApiBudget(
			id = json.getString(ID)!!,
			name = properties.parseNotionString(NAME) ?: "",
			spent = properties.parseNotionDouble(SPENT),
			maximum = properties.parseNotionDouble(MAXIMUM),
			createdAt = json.parseNotionDateTime(CREATED_TIME)
		)
	}
}