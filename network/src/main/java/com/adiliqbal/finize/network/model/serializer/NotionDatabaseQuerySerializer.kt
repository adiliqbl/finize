package com.adiliqbal.finize.network.model.serializer

import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.CURSOR
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.FILTER
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.PAGE_SIZE
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.SORT
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.SORT_FIELD
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.SORT_ORDER
import com.adiliqbal.finize.network.model.request.NotionDatabaseQuery
import com.adiliqbal.finize.network.model.request.PaginationQuery
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal object NotionDatabaseQuerySerializer : KSerializer<NotionDatabaseQuery> {

	override val descriptor: SerialDescriptor =
		buildSerialDescriptor("NotionDatabaseQuery", PolymorphicKind.SEALED)

	override fun serialize(encoder: Encoder, value: NotionDatabaseQuery) =
		with(value) {
			val body = buildJsonObject {
				sortField?.let {
					put(
						SORT,
						buildJsonArray {
							addJsonObject {
								put(SORT_FIELD, JsonPrimitive(sortField))
								put(SORT_ORDER, JsonPrimitive(sortOrder.value))
							}
						}
					)
				}
				filter?.let { put(FILTER, it) }
				put(PAGE_SIZE, JsonPrimitive(pageSize))
				cursor?.let { put(CURSOR, JsonPrimitive(it)) }
			}

			(encoder as JsonEncoder).encodeJsonElement(body)
		}

	override fun deserialize(decoder: Decoder) = NotionDatabaseQuery(PaginationQuery())
}
