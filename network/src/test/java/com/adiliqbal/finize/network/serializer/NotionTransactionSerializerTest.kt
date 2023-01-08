package com.adiliqbal.finize.network.serializer

import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.NotionApiTransaction
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.junit.Test

class NotionTransactionSerializerTest {

	@Test
	fun parse() {
		val json = """
		{
			"object": "page",
			"id": "transaction_id",
			"icon": {
				"type": "file",
				"file": { "url": "https://icon.com" }
			},
			"archived": false,
			"properties": {
				"Tags": {
					"id": "",
					"type": "multi_select",
					"multi_select": [
						{
							"id": "one",
							"name": "bill",
							"color": "blue"
						}
					]
				},
				"Note": {
					"id": "",
					"type": "rich_text",
					"rich_text": [
						{
							"type": "text",
							"text": {
								"content": "Note text",
								"link": null
							},
							"annotations": {
								"bold": false,
								"italic": false,
								"strikethrough": false,
								"underline": false,
								"code": false,
								"color": "default"
							},
							"plain_text": "Note text",
							"href": null
						}
					]
				},
				"Type": {
					"id": "",
					"type": "select",
					"select": {
						"id": "expense_id",
						"name": "Expense",
						"color": "red"
					}
				},
				"Amount": {
					"id": "",
					"type": "number",
					"number": 40
				},
				"From Account": {
					"id": "",
					"type": "relation",
					"relation": [
						{
							"id": "from_account_id"
						}
					],
					"has_more": false
				},
				"Date": {
					"id": "",
					"type": "date",
					"date": {
						"start": "2021-11-14",
						"end": null,
						"time_zone": null
					}
				},
				"To Account": {
					"id": "",
					"type": "relation",
					"relation": [],
					"has_more": false
				},
				"Budget": {
					"id": "",
					"type": "relation",
					"relation": [
						{
							"id": "budget_id"
						}
					],
					"has_more": false
				},
				"Name": {
					"id": "title",
					"type": "title",
					"title": [
						{
							"type": "text",
							"text": {
								"content": "Food",
								"link": null
							},
							"annotations": {
								"bold": false,
								"italic": false,
								"strikethrough": false,
								"underline": false,
								"code": false,
								"color": "default"
							},
							"plain_text": "Food",
							"href": null
						}
					]
				}
			}
		}
		""".trimIndent()

		val transaction = json.decodeJson<NotionApiTransaction>()
		assertEquals("transaction_id", transaction?.id)
		assertEquals("Food", transaction?.name)

		assertEquals("Expense", transaction?.type?.value)
		assertEquals(1, transaction?.tags?.size)

		assertEquals("from_account_id", transaction?.fromAccount)
		assertNull(transaction?.toAccount)
		assertEquals("budget_id", transaction?.budget)
		assertEquals("Note text", transaction?.note)
		assertEquals(LocalDate.parse("2021-11-14").atStartOfDayIn(TimeZone.UTC), transaction?.date)
	}

	@Test
	fun serialize() {
		val transaction = NotionApiTransaction(
			ApiTransaction(
				id = "id",
				name = "name",
				toAccount = "toAccount",
				fromAccount = "fromAccount",
				budget = "budgetId",
				tags = listOf("one", "two")
			)
		)
		transaction.toJson()
	}
}
