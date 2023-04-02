package com.adiliqbal.finize.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adiliqbal.finize.database.base.BaseDatabaseTest
import com.adiliqbal.finize.database.model.ExchangeRateEntity
import junit.framework.Assert.*
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ExchangeRateDaoTest : BaseDatabaseTest() {

    @Test
    fun insertExchangeRates() = runTest {
        db.exchangeRateDao().insert(
            ExchangeRateEntity("from", "to", 100f, Instant.DISTANT_FUTURE),
            ExchangeRateEntity("to", "from", 0.01f, Instant.DISTANT_FUTURE)
        )

        assertNotNull(db.exchangeRateDao().get("from", "to"))
        assertNotNull(db.exchangeRateDao().get("to", "from"))
    }

    @Test
    fun getExchangeRate() = runTest {
        val rate = ExchangeRateEntity("from", "to", 100f, Instant.DISTANT_FUTURE)
        db.exchangeRateDao().insert(rate)

        assertNull(db.exchangeRateDao().get("from", "toNew"))
        assertEquals(rate, db.exchangeRateDao().get("from", "to"))
    }

    @Test
    fun deleteExchangeRates() = runTest {
        db.exchangeRateDao().insert(
            ExchangeRateEntity("from", "to", 100f, Instant.DISTANT_FUTURE),
            ExchangeRateEntity("to", "from", 0.01f, Instant.DISTANT_FUTURE)
        )
        assertNotNull(db.exchangeRateDao().get("from", "to"))
        assertNotNull(db.exchangeRateDao().get("to", "from"))

        db.exchangeRateDao().delete("from")
        assertNull(db.exchangeRateDao().get("from", "to"))
        assertNull(db.exchangeRateDao().get("to", "from"))
    }

    @Test
    fun clear() = runTest {
        db.exchangeRateDao().insert(
            ExchangeRateEntity("from", "to", 100f, Instant.DISTANT_FUTURE),
            ExchangeRateEntity("to", "from", 0.01f, Instant.DISTANT_FUTURE)
        )
        assertNotNull(db.exchangeRateDao().get("from", "to"))

        db.exchangeRateDao().clear()
        assertNull(db.exchangeRateDao().get("from", "to"))
        assertNull(db.exchangeRateDao().get("to", "from"))
    }
}
