package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDateTime
import com.adiliqbal.finize.network.extensions.parseNotionDouble
import com.adiliqbal.finize.network.extensions.parseNotionString
import com.adiliqbal.finize.network.extensions.toNotionNumber
import com.adiliqbal.finize.network.extensions.toNotionTitle
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
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject

internal object NotionAccountSerializer : KSerializer<ApiAccount> {

	private const val ID = "id"
	private const val NAME = "Name"
	private const val CURRENT_BALANCE = "Current Balance"
	private const val STARTING_BALANCE = "Starting Balance"
	private const val CREATED_TIME = "created_time"

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("NotionAccount", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: ApiAccount) {
		val body = buildJsonObject {
			put(NAME, value.name.toNotionTitle())
			put(CURRENT_BALANCE, value.currentBalance.toNotionNumber())
			put(STARTING_BALANCE, value.startingBalance.toNotionNumber())
		}

		encoder.encodeString(body.toJson())
	}

	override fun deserialize(decoder: Decoder): ApiAccount {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return ApiAccount(
			id = json.getString(ID)!!,
			name = properties.parseNotionString(NAME) ?: "",
			currentBalance = properties.parseNotionDouble(CURRENT_BALANCE),
			startingBalance = properties.parseNotionDouble(STARTING_BALANCE),
			createdAt = json.parseNotionDateTime(CREATED_TIME)
		)
	}
}