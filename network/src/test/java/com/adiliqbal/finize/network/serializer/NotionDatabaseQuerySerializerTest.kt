package com.adiliqbal.finize.network.serializer

import com.adiliqbal.finize.model.enums.SortOrder
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.extensions.getInt
import com.adiliqbal.finize.network.extensions.getString
import com.adiliqbal.finize.network.extensions.toNotionFilter
import com.adiliqbal.finize.network.model.request.NotionDatabaseQuery
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.CURSOR
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.FILTER
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.PAGE_SIZE
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.SORT
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.SORT_FIELD
import com.adiliqbal.finize.network.model.request.NotionDatabaseKeys.SORT_ORDER
import com.adiliqbal.finize.network.model.request.PaginationQuery
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import junit.framework.Assert.*
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.junit.Test

class NotionDatabaseQuerySerializerTest {

	@Test
	fun serialize() {
		var query = NotionDatabaseQuery(
			PaginationQuery(
				sortField = "date",
				sortOrder = SortOrder.DESCENDING,
				filter = TransactionsFilter(
					accountTo = "to",
					accountFrom = "from"
				).toNotionFilter(),
				pageSize = 20
			)
		).toJson().decodeJson<JsonObject>()

		assertTrue(query?.containsKey(SORT) == true)
		assertEquals("date", query?.get(SORT)?.jsonArray?.get(0)?.jsonObject?.getString(SORT_FIELD))
		assertEquals(
			SortOrder.DESCENDING.value,
			query?.get(SORT)?.jsonArray?.get(0)?.jsonObject?.getString(SORT_ORDER)
		)
		assertTrue(query?.containsKey(FILTER) == true)
		assertFalse(query?.containsKey(CURSOR) == true)
		assertEquals(20, query?.getInt(PAGE_SIZE))

		query = NotionDatabaseQuery(
			PaginationQuery(
				sortField = "date",
				filter = TransactionsFilter(
					accountTo = "to",
					accountFrom = "from"
				).toNotionFilter(),
				cursor = "cursor",
				pageSize = 20
			)
		).toJson().decodeJson<JsonObject>()

		assertTrue(query?.containsKey(SORT) == true)
		assertEquals("date", query?.get(SORT)?.jsonArray?.get(0)?.jsonObject?.getString(SORT_FIELD))
		assertEquals(
			SortOrder.DESCENDING.value,
			query?.get(SORT)?.jsonArray?.get(0)?.jsonObject?.getString(SORT_ORDER)
		)
		assertTrue(query?.containsKey(FILTER) == true)
		assertEquals("cursor", query?.getString(CURSOR))
		assertEquals(20, query?.getInt(PAGE_SIZE))
	}
}
