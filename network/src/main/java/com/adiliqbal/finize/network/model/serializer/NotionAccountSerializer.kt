package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.common.util.CurrencyUtil
import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.common.util.date
import com.adiliqbal.finize.model.enums.toAccountType
import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.parseNotionDate
import com.adiliqbal.finize.network.extensions.parseNotionDateTime
import com.adiliqbal.finize.network.extensions.parseNotionDouble
import com.adiliqbal.finize.network.extensions.parseNotionRelation
import com.adiliqbal.finize.network.extensions.parseNotionString
import com.adiliqbal.finize.network.extensions.toNotionNumber
import com.adiliqbal.finize.network.extensions.toNotionTitle
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.NotionApiAccount
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
internal object NotionAccountSerializer : KSerializer<NotionApiAccount> {

	private const val ID = "id"
	private const val NAME = "Name"
	private const val BALANCE = "Current Balance"
	private const val CURRENCY = "Currency"
	private const val BUDGET = "Budget"
	private const val BUDGET_EXPIRE_DATE = "Budget Expire At"
	private const val TYPE = "Type"
	private const val CREATED_TIME = "created_time"

	override val descriptor: SerialDescriptor =
		buildSerialDescriptor("NotionAccount", PolymorphicKind.SEALED)

	override fun serialize(encoder: Encoder, value: NotionApiAccount) {
		val body = buildJsonObject {
			put(NAME, value.name.toNotionTitle())
			put(BALANCE, value.balance.toNotionNumber())
		}

		(encoder as JsonEncoder).encodeJsonElement(body)
	}

	override fun deserialize(decoder: Decoder): NotionApiAccount {
		val json = (decoder as JsonDecoder).decodeJsonElement() as JsonObject
		val properties = json["properties"]!!.jsonObject
		return NotionApiAccount(
			ApiAccount(
				id = json.getString(ID)!!,
				name = properties.parseNotionString(NAME) ?: "",
				balance = properties.parseNotionDouble(BALANCE),
				budget = properties.parseNotionRelation(BUDGET)
					?.takeIf { it.isNotEmpty() }?.get(0),
				budgetExpireDate = properties.parseNotionDate(BUDGET_EXPIRE_DATE)?.date,
				currency = properties.parseNotionString(CURRENCY)
					?: CurrencyUtil.default.currencyCode,
				type = properties.parseNotionString(TYPE).toAccountType(),
				createdAt = json.parseNotionDateTime(CREATED_TIME) ?: DateUtil.now()
			)
		)
	}
}