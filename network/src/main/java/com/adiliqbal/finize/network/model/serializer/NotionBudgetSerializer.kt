package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDateTime
import com.adiliqbal.finize.network.extensions.parseNotionDouble
import com.adiliqbal.finize.network.extensions.parseNotionString
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
import kotlinx.serialization.json.jsonObject

internal object NotionBudgetSerializer : KSerializer<ApiBudget> {
	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("NotionBudget", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: ApiBudget) {
		encoder.encodeString(value.toJson())
	}

	override fun deserialize(decoder: Decoder): ApiBudget {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return ApiBudget(
			id = json.getString("id")!!,
			name = properties.parseNotionString("Name") ?: "",
			spent = properties.parseNotionDouble("Spent"),
			maximum = properties.parseNotionDouble("Maximum"),
			createdAt = json.parseNotionDateTime("created_time")
		)
	}
}