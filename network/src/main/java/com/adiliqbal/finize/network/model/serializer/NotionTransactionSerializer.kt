package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDate
import com.adiliqbal.finize.network.extensions.parseNotionRelation
import com.adiliqbal.finize.network.extensions.parseNotionString
import com.adiliqbal.finize.network.extensions.parseNotionTag
import com.adiliqbal.finize.network.extensions.parseNotionTags
import com.adiliqbal.finize.network.model.ApiTransaction
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

internal object NotionTransactionSerializer : KSerializer<ApiTransaction> {
	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("NotionTransaction", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: ApiTransaction) {
		encoder.encodeString(value.toJson())
	}

	override fun deserialize(decoder: Decoder): ApiTransaction {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return ApiTransaction(
			id = json.getString("id")!!,
			name = properties.parseNotionString("Name") ?: "",
			type = properties.parseNotionTag("Type"),
			toAccount = properties.parseNotionRelation("To Account")
				?.takeIf { it.isNotEmpty() }?.get(0),
			fromAccount = properties.parseNotionRelation("From Account")
				?.takeIf { it.isNotEmpty() }?.get(0),
			budgets = properties.parseNotionRelation("Budget"),
			tags = properties.parseNotionTags("Tags"),
			note = properties.parseNotionString("Note"),
			date = properties.parseNotionDate("Date")
		)
	}
}