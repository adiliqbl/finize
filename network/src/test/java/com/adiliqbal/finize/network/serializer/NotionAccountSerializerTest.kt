package com.adiliqbal.finize.network.serializer

import com.adiliqbal.finize.model.enums.AccountType
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.NotionApiAccount
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NotionAccountSerializerTest {

	@Test
	fun parse() {
		val json = """
		{
			"object": "page",
			"id": "account_id",
			"created_time": "2023-04-12T09:01:00.000Z",
			"cover": null,
			"icon": {
				"type": "file",
				"file": { "url": "https://icon.com" }
			},
			"archived": false,
			"properties": {
				"Expense Transactions": {
					"id": "",
					"type": "relation",
					"relation": [],
					"has_more": false
				},
				"Current Balance": {
					"id": "",
					"type": "formula",
					"formula": {
						"type": "number",
						"number": 10
					}
				},
				"Income Transactions": {
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
				"Starting Balance": {
					"id": "starting",
					"type": "number",
					"number": null
				},
				"Currency": {
					"id": "",
					"type": "select",
					"select": {
						"id": "currency_id",
						"name": "CURR",
						"color": "red"
					}
				},
				"Type": {
					"id": "",
					"type": "select",
					"select": {
						"id": "type_id",
						"name": "INVESTMENT",
						"color": "red"
					}
				},
				"Name": {
					"id": "title",
					"type": "title",
					"title": [
						{
							"type": "text",
							"text": {
								"content": "Account",
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
							"plain_text": "Account",
							"href": null
						}
					]
				}
			}
		}
		""".trimIndent()

		val account = json.decodeJson<NotionApiAccount>()
		assertEquals("account_id", account?.id)
		assertEquals("Account", account?.name)
		assertEquals("CURR", account?.currency)
		assertEquals(AccountType.INVESTMENT, account?.type)
		assertEquals(10.0, account?.balance)
		assertEquals("2023-04-12T09:01:00Z", account?.createdAt?.toString())
	}

	@Test
	fun serialize() {
		val account = NotionApiAccount(
			ApiAccount(
				id = "id",
				name = "name",
				balance = 50.0,
				currency = "CURR",
				type = AccountType.DEPOSIT
			)
		)
		account.toJson()
	}
}
