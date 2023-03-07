package com.adiliqbal.finize.network.request

import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.extensions.toNotionFilter
import junit.framework.TestCase.assertNotNull
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.junit.Test

class ApiTransactionFilterTest {

	@Test
	fun toJson() {
		assertNotNull(TransactionsFilter(name = "Name").toNotionFilter())
		assertNotNull(TransactionsFilter(accountTo = "to", accountFrom = "from").toNotionFilter())
		assertNotNull(TransactionsFilter(accountTo = "to", accountFrom = "from").toNotionFilter())
		assertNotNull(TransactionsFilter(accountTo = "to").toNotionFilter())
		assertNotNull(TransactionsFilter(accountTo = "from").toNotionFilter())
		assertNotNull(
			TransactionsFilter(
				date = LocalDate.parse("2022-12-01").atStartOfDayIn(
					TimeZone.UTC
				)
			).toNotionFilter()
		)
		assertNotNull(
			TransactionsFilter(
				dateFrom = LocalDate.parse("2022-12-01").atStartOfDayIn(TimeZone.UTC),
				dateTo = LocalDate.parse("2022-12-10").atStartOfDayIn(TimeZone.UTC)
			).toNotionFilter()
		)
	}
}
