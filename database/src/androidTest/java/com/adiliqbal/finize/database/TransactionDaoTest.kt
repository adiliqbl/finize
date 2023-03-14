package com.adiliqbal.finize.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adiliqbal.finize.database.base.BaseDatabaseTest
import com.adiliqbal.finize.database.model.fake.FakeEntity
import com.adiliqbal.finize.database.util.result
import com.adiliqbal.finize.model.filter.TransactionsFilter
import junit.framework.Assert.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class TransactionDaoTest : BaseDatabaseTest() {

    private val transaction = FakeEntity.transaction(id = "t1", accountFrom = "a1", budget = "b1")
    private val transactions =
        listOf(
            FakeEntity.transaction(
                id = "t1",
                accountFrom = "a1",
                name = "Transaction One",
                budget = "b1",
                date = LocalDate(2022, 8, 1).atStartOfDayIn(TimeZone.UTC)
            ),
            FakeEntity.transaction(
                id = "t2",
                accountFrom = "a1",
                accountTo = "a2",
                budget = "b2",
                name = "Transaction One",
                date = LocalDate(2022, 8, 10).atStartOfDayIn(TimeZone.UTC)
            ),
            FakeEntity.transaction(
                id = "t3",
                accountFrom = null,
                accountTo = "a2",
                budget = "b1",
                name = "Transaction Three",
                date = LocalDate(2022, 8, 20).atStartOfDayIn(TimeZone.UTC)
            )
        )

    @Before
    fun setup() = runTest {
        db.accountDao().insert(FakeEntity.account(id = "a1"))
        db.accountDao().insert(FakeEntity.account(id = "a2"))
        db.budgetDao().insert(FakeEntity.budget(id = "b1"))
        db.budgetDao().insert(FakeEntity.budget(id = "b2"))
    }

    @Test
    fun insertTransaction() = runTest {
        db.transactionDao().upsert(transaction)
        db.transactionDao().upsert(transaction)

        assertEquals(transaction.id, db.transactionDao().get(transaction.id).first()?.id)
    }

    @Test
    fun getTransactions() = runTest {
        db.transactionDao().upsert(transaction)
        db.transactionDao().upsert(transaction.copy(id = "transactionId2"))

        assertEquals(2, db.transactionDao().getAll().result()?.size)
    }

    @Test
    fun filterTransactionsByName() = runTest {
        db.transactionDao().upsert(transactions)

        assertEquals(
            transactions.size,
            db.transactionDao().filter(TransactionsFilter(name = "Transaction")).result()?.size
        )
        assertEquals(2, db.transactionDao().filter(TransactionsFilter(name = "One")).result()?.size)
    }

    @Test
    fun filterTransactionsByDate() = runTest {
        db.transactionDao().upsert(transactions)

        assertEquals(
            1,
            db.transactionDao()
                .filter(TransactionsFilter(date = LocalDate(2022, 8, 10).atStartOfDayIn(TimeZone.UTC)))
                .result()
                ?.size
        )
        assertEquals(
            2,
            db.transactionDao()
                .filter(
                    TransactionsFilter(dateFrom = LocalDate(2022, 8, 10).atStartOfDayIn(TimeZone.UTC))
                )
                .result()
                ?.size
        )
        assertEquals(
            2,
            db.transactionDao()
                .filter(
                    TransactionsFilter(dateTo = LocalDate(2022, 8, 10).atStartOfDayIn(TimeZone.UTC))
                )
                .result()
                ?.size
        )
        assertEquals(
            3,
            db.transactionDao()
                .filter(
                    TransactionsFilter(
                        dateFrom = LocalDate(2022, 8, 1).atStartOfDayIn(TimeZone.UTC),
                        dateTo = LocalDate(2022, 8, 30).atStartOfDayIn(TimeZone.UTC)
                    )
                )
                .result()
                ?.size
        )
    }

    @Test
    fun filterTransactionsByAccount() = runTest {
        db.transactionDao().upsert(transactions)

        assertEquals(
            2, db.transactionDao().filter(TransactionsFilter(accountFrom = "a1")).result()?.size
        )
        assertEquals(0, db.transactionDao().filter(TransactionsFilter(accountTo = "a1")).result()?.size)
        assertEquals(
            0, db.transactionDao().filter(TransactionsFilter(accountFrom = "a2")).result()?.size
        )
        assertEquals(2, db.transactionDao().filter(TransactionsFilter(accountTo = "a2")).result()?.size)
        assertEquals(
            1,
            db.transactionDao()
                .filter(TransactionsFilter(accountFrom = "a1", accountTo = "a2"))
                .result()
                ?.size
        )
        assertEquals(
            0,
            db.transactionDao()
                .filter(TransactionsFilter(accountFrom = "a2", accountTo = "a1"))
                .result()
                ?.size
        )
        assertEquals(
            2,
            db.transactionDao()
                .filter(TransactionsFilter(accountFrom = "a1", accountTo = "a1"))
                .result()
                ?.size
        )
        assertEquals(
            2,
            db.transactionDao()
                .filter(TransactionsFilter(accountFrom = "a2", accountTo = "a2"))
                .result()
                ?.size
        )
    }

    @Test
    fun filterTransactionsByBudget() = runTest {
        db.transactionDao().upsert(transactions)

        assertEquals(2, db.transactionDao().filter(TransactionsFilter(budget = "b1")).result()?.size)
        assertEquals(1, db.transactionDao().filter(TransactionsFilter(budget = "b2")).result()?.size)
    }

    @Test
    fun filterTransactions() = runTest {
        db.transactionDao().upsert(transactions)

        assertEquals(
            1,
            db.transactionDao()
                .filter(TransactionsFilter(accountTo = "a2", name = "One", budget = "b2"))
                .result()
                ?.size
        )
        assertEquals(
            1,
            db.transactionDao()
                .filter(TransactionsFilter(accountTo = "a2", budget = "b1"))
                .result()
                ?.size
        )
    }

    @Test
    fun deleteTransaction() = runTest {
        db.transactionDao().upsert(transaction)
        db.transactionDao().delete(transaction)

        assertNull(db.transactionDao().get(transaction.id).firstOrNull())
        assertTrue(db.transactionDao().getAll().result()?.size == 0)
    }
}
