package com.adiliqbal.finize.network.request

import com.adiliqbal.finize.common.util.instant
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.extensions.toNotionFilter
import junit.framework.TestCase.assertNotNull
import kotlinx.datetime.LocalDate
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
                dateFrom = LocalDate.parse("2022-12-01").instant,
                dateTo = LocalDate.parse("2022-12-01").instant
            )
                .toNotionFilter()
        )
        assertNotNull(
            TransactionsFilter(
                dateFrom = LocalDate.parse("2022-12-01").instant,
                dateTo = LocalDate.parse("2022-12-10").instant
            )
                .toNotionFilter()
        )
    }
}
