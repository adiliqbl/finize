package com.adiliqbal.finize.network

import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NotionBudgetSerializerTest {

	@Test
	fun parse() {
		val json = """
		{
			"object": "page",
			"id": "budget_id",
			"created_time": "2021-03-05T17:36:00.000Z",
			"cover": null,
			"icon": {
				"type": "file",
				"file": { "url": "https://icon.com" }
			},
			"archived": false,
			"properties": {
				"Spent": {
					"id": "spent",
					"type": "rollup",
					"rollup": {
						"type": "number",
						"number": 10,
						"function": "sum"
					}
				},
				"Maximum": {
					"id": "max",
					"type": "number",
					"number": 50
				},
				"Transactions": {
					"id": "",
					"type": "relation",
					"relation": [
						{
							"id": "one"
						},
						{
							"id": "two"
						}
					],
					"has_more": true
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

		val budget = json.decodeJson<ApiBudget>()
		assertEquals("budget_id", budget?.id)
		assertEquals("Food", budget?.name)
		assertEquals(10.0, budget?.spent)
		assertEquals(50.0, budget?.maximum)
	}
}
