package com.adiliqbal.finize.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adiliqbal.finize.database.base.BaseDatabaseTest
import com.adiliqbal.finize.database.model.fake.FakeEntity
import junit.framework.Assert.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class BaseDaoTest : BaseDatabaseTest() {

    @Test
    fun insert() = runTest {
        db.userDao().insert(FakeEntity.user("one"))
        db.userDao().getAll().firstOrNull().let { data ->
            assertNotNull(data)
            assertEquals("one", data?.get(0)?.id)
        }
    }

    @Test
    fun update() = runTest {
        db.userDao().insert(FakeEntity.user("one", name = "old name"))
        db.userDao().get("one").first().let { assertEquals("old name", it?.name) }

        db.userDao().update(FakeEntity.user("one", name = "new name"))
        db.userDao().get("one").first().let { assertEquals("new name", it?.name) }
    }

    @Test
    fun upsertSingle() = runTest {
        db.userDao().insert(FakeEntity.user("one", name = "old name"))
        db.userDao().get("one").first().let { assertEquals("old name", it?.name) }

        db.userDao().upsert(FakeEntity.user("one", name = "new name"))
        db.userDao().get("one").first().let { assertEquals("new name", it?.name) }
    }

    @Test
    fun upsertList() = runTest {
        var users =
            mutableListOf(
                FakeEntity.user("one", name = "old name"), FakeEntity.user("two", name = "old name")
            )

        db.userDao().insert(users)

        db.userDao().getAll().firstOrNull().let { data ->
            assertNotNull(data)
            assertEquals(2, data?.size)
        }

        users =
            mutableListOf(
                FakeEntity.user("one", name = "updated"),
                FakeEntity.user("two", name = "updated"),
                FakeEntity.user("three"),
                FakeEntity.user("four")
            )

        db.userDao().upsert(users)

        db.userDao().getAll().firstOrNull().let { data ->
            assertNotNull(data)
            assertEquals(4, data?.size)

            data?.find { it.id == "one" }?.let { assertEquals("Updated name", "updated", it.name) }
        }
    }

    @Test
    fun delete() = runTest {
        db.userDao().insert(FakeEntity.user("one"))
        db.userDao().insert(FakeEntity.user("two"))
        assertNotNull(db.userDao().get("one").firstOrNull())
        assertNotNull(db.userDao().get("two").firstOrNull())

        db.userDao().delete(FakeEntity.user("one"))
        db.userDao().delete("two")
        assertNull(db.userDao().get("one").firstOrNull())
        assertNull(db.userDao().get("two").firstOrNull())
    }

    @Test
    fun clear() = runTest {
        db.userDao().insert(FakeEntity.user("one"))
        db.userDao().insert(FakeEntity.user("two"))
        assertNotNull(db.userDao().get("one").firstOrNull())
        assertNotNull(db.userDao().get("two").firstOrNull())

        db.userDao().clear()
        assertTrue(db.userDao().getAll().firstOrNull().isNullOrEmpty())
    }
}
