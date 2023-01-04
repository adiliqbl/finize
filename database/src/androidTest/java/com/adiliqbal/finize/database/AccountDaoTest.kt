package com.adiliqbal.finize.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adiliqbal.finize.database.base.BaseDatabaseTest
import com.adiliqbal.finize.database.model.fake.FakeEntity
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class AccountDaoTest : BaseDatabaseTest() {

	private val account = FakeEntity.account()

	@Test
	fun insertAccount() = runTest {
		db.accountDao().upsert(account)
		db.accountDao().upsert(account)

		val data = db.accountDao().get(account.id).first()
		assertEquals(account.id, data?.id)
	}

	@Test
	fun getAccounts() = runTest {
		db.accountDao().upsert(account)
		db.accountDao().upsert(account.copy(id = "account2"))

		val data = db.accountDao().getAll().firstOrNull()
		assertEquals(2, data?.size)
	}

	@Test
	fun deleteAccount() = runTest {
		db.accountDao().upsert(account)
		db.accountDao().delete(account)

		assertNull(db.accountDao().get(account.id).firstOrNull())
		assertTrue(db.accountDao().getAll().firstOrNull()?.size == 0)
	}
}
