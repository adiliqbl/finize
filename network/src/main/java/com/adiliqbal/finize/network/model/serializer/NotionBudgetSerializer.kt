package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.common.util.date
import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDate
import com.adiliqbal.finize.network.extensions.parseNotionDateTime
import com.adiliqbal.finize.network.extensions.parseNotionDouble
import com.adiliqbal.finize.network.extensions.parseNotionString
import com.adiliqbal.finize.network.extensions.toNotionDate
import com.adiliqbal.finize.network.extensions.toNotionNumber
import com.adiliqbal.finize.network.extensions.toNotionTitle
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.NotionApiBudget
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal object NotionBudgetSerializer : KSerializer<NotionApiBudget> {

	private const val ID = "id"
	private const val NAME = "Name"
	private const val LIMIT = "Limit"
	private const val EXPIRE_AT = "Expire At"
	private const val CREATED_TIME = "created_time"

	override val descriptor: SerialDescriptor =
		buildSerialDescriptor("NotionBudget", PolymorphicKind.SEALED)

	override fun serialize(encoder: Encoder, value: NotionApiBudget) {
		val body = buildJsonObject {
			put(NAME, value.name.toNotionTitle())
			put(LIMIT, value.limit.toNotionNumber())
			value.expireAt?.let { put(EXPIRE_AT, it.toNotionDate()) }
		}

		(encoder as JsonEncoder).encodeJsonElement(body)
	}

	override fun deserialize(decoder: Decoder): NotionApiBudget {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return NotionApiBudget(
			ApiBudget(
				id = json.getString(ID)!!,
				name = properties.parseNotionString(NAME) ?: "",
				limit = properties.parseNotionDouble(LIMIT),
				expireAt = properties.parseNotionDate(EXPIRE_AT)?.date,
				createdAt = json.parseNotionDateTime(CREATED_TIME) ?: DateUtil.now()
			)
		)
	}
}