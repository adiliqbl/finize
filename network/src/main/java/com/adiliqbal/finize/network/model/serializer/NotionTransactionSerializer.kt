package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDate
import com.adiliqbal.finize.network.extensions.parseNotionRelation
import com.adiliqbal.finize.network.extensions.parseNotionString
import com.adiliqbal.finize.network.extensions.parseNotionTag
import com.adiliqbal.finize.network.extensions.parseNotionTags
import com.adiliqbal.finize.network.extensions.toNotionDate
import com.adiliqbal.finize.network.extensions.toNotionRelation
import com.adiliqbal.finize.network.extensions.toNotionRichString
import com.adiliqbal.finize.network.extensions.toNotionTag
import com.adiliqbal.finize.network.extensions.toNotionTags
import com.adiliqbal.finize.network.extensions.toNotionTitle
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
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

internal object NotionTransactionSerializer : KSerializer<ApiTransaction> {

	internal const val ID = "id"
	internal const val NAME = "Name"
	internal const val TYPE = "Type"
	internal const val TO_ACCOUNT = "To Account"
	internal const val FROM_ACCOUNT = "From Account"
	internal const val BUDGET = "Budget"
	private const val TAGS = "Tags"
	private const val NOTE = "Note"
	internal const val DATE = "Date"

	override val descriptor: SerialDescriptor =
		PrimitiveSerialDescriptor("NotionTransaction", PrimitiveKind.STRING)

	override fun serialize(encoder: Encoder, value: ApiTransaction) {
		val body = buildJsonObject {
			put(NAME, value.name.toNotionTitle())
			put(DATE, value.date.toNotionDate())
			value.type?.let { put(TYPE, it.toNotionTag()) }
			value.fromAccount?.let { put(FROM_ACCOUNT, it.toNotionRelation()) }
			value.toAccount?.let { put(FROM_ACCOUNT, it.toNotionRelation()) }
			value.budgets?.let { put(BUDGET, it.toNotionRelation()) }
			value.tags?.let { put(TAGS, it.toNotionTags()) }
			value.note?.let { put(NOTE, it.toNotionRichString()) }
		}

		encoder.encodeString(body.toJson())
	}

	override fun deserialize(decoder: Decoder): ApiTransaction {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return ApiTransaction(
			id = json.getString(ID)!!,
			name = properties.parseNotionString(NAME) ?: "",
			type = properties.parseNotionTag(TYPE),
			toAccount = properties.parseNotionRelation(TO_ACCOUNT)
				?.takeIf { it.isNotEmpty() }?.get(0),
			fromAccount = properties.parseNotionRelation(FROM_ACCOUNT)
				?.takeIf { it.isNotEmpty() }?.get(0),
			budgets = properties.parseNotionRelation(BUDGET),
			tags = properties.parseNotionTags(TAGS),
			note = properties.parseNotionString(NOTE),
			date = properties.parseNotionDate(DATE) ?: DateUtil.currentDay()
		)
	}
}