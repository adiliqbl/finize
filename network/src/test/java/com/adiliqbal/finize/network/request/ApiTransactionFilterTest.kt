package com.adiliqbal.finize.network.request

import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.extensions.toNotionFilter
import junit.framework.TestCase.assertNotNull
import kotlinx.datetime.LocalDate
import org.junit.Test

class ApiTransactionFilterTest {

	@Test
	fun toJson() {
		assertNotNull(TransactionsFilter(name = "Name").toNotionFilter())
		assertNotNull(TransactionsFilter(toAccount = "to", fromAccount = "from").toNotionFilter())
		assertNotNull(TransactionsFilter(toAccount = "to", fromAccount = "from").toNotionFilter())
		assertNotNull(TransactionsFilter(toAccount = "to").toNotionFilter())
		assertNotNull(TransactionsFilter(toAccount = "from").toNotionFilter())
		assertNotNull(TransactionsFilter(date = LocalDate.parse("2022-12-01")).toNotionFilter())
		assertNotNull(
			TransactionsFilter(
				dateFrom = LocalDate.parse("2022-12-01"),
				dateTo = LocalDate.parse("2022-12-10")
			).toNotionFilter()
		)
	}
}
