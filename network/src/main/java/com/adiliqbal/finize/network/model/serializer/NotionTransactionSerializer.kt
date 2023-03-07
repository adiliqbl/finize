package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.common.util.CurrencyUtil
import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDate
import com.adiliqbal.finize.network.extensions.parseNotionMultiselect
import com.adiliqbal.finize.network.extensions.parseNotionRelation
import com.adiliqbal.finize.network.extensions.parseNotionString
import com.adiliqbal.finize.network.extensions.toNotionDate
import com.adiliqbal.finize.network.extensions.toNotionMultiselect
import com.adiliqbal.finize.network.extensions.toNotionRelation
import com.adiliqbal.finize.network.extensions.toNotionRichString
import com.adiliqbal.finize.network.extensions.toNotionSelect
import com.adiliqbal.finize.network.extensions.toNotionTitle
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.NotionApiTransaction
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
internal object NotionTransactionSerializer : KSerializer<NotionApiTransaction> {

	internal const val ID = "id"
	internal const val NAME = "Name"
	internal const val CATEGORY = "Type"
	internal const val TO_ACCOUNT = "To Account"
	internal const val FROM_ACCOUNT = "From Account"
	internal const val BUDGET = "Budget"
	private const val CURRENCY = "Currency"
	private const val NOTE = "Note"
	internal const val DATE = "Date"

	override val descriptor: SerialDescriptor =
		buildSerialDescriptor("NotionTransaction", PolymorphicKind.SEALED)

	override fun serialize(encoder: Encoder, value: NotionApiTransaction) {
		val body = buildJsonObject {
			put(NAME, value.name.toNotionTitle())
			put(DATE, value.date.toNotionDate())
			put(CATEGORY, value.category.toNotionMultiselect())
			value.accountFrom?.let { put(FROM_ACCOUNT, it.toNotionRelation()) }
			value.accountTo?.let { put(FROM_ACCOUNT, it.toNotionRelation()) }
			value.budget?.let { put(BUDGET, it.toNotionRelation()) }
			put(CURRENCY, value.currency.toNotionSelect())
			value.note?.let { put(NOTE, it.toNotionRichString()) }
		}

		(encoder as JsonEncoder).encodeJsonElement(body)
	}

	override fun deserialize(decoder: Decoder): NotionApiTransaction {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return NotionApiTransaction(
			ApiTransaction(
				id = json.getString(ID)!!,
				name = properties.parseNotionString(NAME) ?: "",
				category = properties.parseNotionMultiselect(CATEGORY)!!,
				accountTo = properties.parseNotionRelation(TO_ACCOUNT)
					?.takeIf { it.isNotEmpty() }?.get(0),
				accountFrom = properties.parseNotionRelation(FROM_ACCOUNT)
					?.takeIf { it.isNotEmpty() }?.get(0),
				budget = properties.parseNotionRelation(BUDGET)
					?.takeIf { it.isNotEmpty() }?.get(0),
				currency = properties.parseNotionString(CURRENCY)
					?: CurrencyUtil.default.currencyCode,
				note = properties.parseNotionString(NOTE),
				date = properties.parseNotionDate(DATE) ?: DateUtil.now()
			)
		)
	}
}