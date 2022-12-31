package com.adiliqbal.finize.network

import com.adiliqbal.finize.network.model.request.TransactionsFilter
import com.adiliqbal.finize.network.util.AppJson.toJson
import kotlinx.datetime.LocalDate
import org.junit.Test

class NotionTransactionFilterSerializerTest {

	@Test
	fun serialize() {
		TransactionsFilter(name = "Name").toJson()
		TransactionsFilter(toAccount = "to", fromAccount = "from").toJson()
		TransactionsFilter(toAccount = "to", fromAccount = "from").toJson()
		TransactionsFilter(toAccount = "to").toJson()
		TransactionsFilter(toAccount = "from").toJson()
		TransactionsFilter(date = LocalDate.parse("2022-12-01")).toJson()
		TransactionsFilter(
			dateFrom = LocalDate.parse("2022-12-01"),
			dateTo = LocalDate.parse("2022-12-10")
		).toJson()
	}
}
