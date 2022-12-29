package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseDateTime
import com.adiliqbal.finize.network.extensions.parseDouble
import com.adiliqbal.finize.network.extensions.parseString
import com.adiliqbal.finize.network.model.ApiAccount
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

internal object NotionAccountSerializer : KSerializer<ApiAccount> {
	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("NotionAccount", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: ApiAccount) {
		encoder.encodeString(value.toJson())
	}

	override fun deserialize(decoder: Decoder): ApiAccount {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return ApiAccount(
			id = json.getString("id")!!,
			name = properties.parseString("Name") ?: "",
			currentBalance = properties.parseDouble("Current Balance"),
			startingBalance = properties.parseDouble("Starting Balance"),
			createdAt = json.parseDateTime("created_time")
		)
	}
}