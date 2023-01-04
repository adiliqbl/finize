package com.adiliqbal.finize.network.request

import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.model.request.toJson
import junit.framework.TestCase.assertNotNull
import kotlinx.datetime.LocalDate
import org.junit.Test

class ApiTransactionFilterTest {

	@Test
	fun toJson() {
		assertNotNull(TransactionsFilter(name = "Name").toJson())
		assertNotNull(TransactionsFilter(toAccount = "to", fromAccount = "from").toJson())
		assertNotNull(TransactionsFilter(toAccount = "to", fromAccount = "from").toJson())
		assertNotNull(TransactionsFilter(toAccount = "to").toJson())
		assertNotNull(TransactionsFilter(toAccount = "from").toJson())
		assertNotNull(TransactionsFilter(date = LocalDate.parse("2022-12-01")).toJson())
		assertNotNull(
			TransactionsFilter(
				dateFrom = LocalDate.parse("2022-12-01"),
				dateTo = LocalDate.parse("2022-12-10")
			).toJson()
		)
	}
}
