package com.adiliqbal.finize.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adiliqbal.finize.database.base.BaseDatabaseTest
import com.adiliqbal.finize.database.model.UserEntity
import com.adiliqbal.finize.database.model.fake.FakeEntity
import junit.framework.Assert.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class UserDaoTest : BaseDatabaseTest() {

	private val user = FakeEntity.user()

	@Test
	fun insertUser() = runTest {
		db.userDao().upsert(user)
		db.userDao().upsert(user)

		val data = db.userDao().get(user.id).first()
		assertEquals(user.id, data?.id)
		assertEquals(user.profile.currency, data?.profile?.currency)
	}

	@Test
	fun updateUser() = runTest {
		db.userDao().upsert(user)

		var data = db.userDao().get(user.id).first()
		assertEquals(user.name, data?.name)
		assertEquals(user.profile.currency, data?.profile?.currency)

		db.userDao().update(UserEntity(user.id, "New Name", "email", "image"))

		data = db.userDao().get(user.id).first()
		assertEquals("New Name", data?.name)
		assertEquals("email", data?.email)
		assertEquals("image", data?.image)
		assertEquals(user.profile.currency, data?.profile?.currency)
	}

	@Test
	fun getUsers() = runTest {
		db.userDao().upsert(user)
		db.userDao().upsert(user.copy(id = "user2"))

		val data = db.userDao().getAll().firstOrNull()
		assertEquals(2, data?.size)
	}

	@Test
	fun deleteUser() = runTest {
		db.userDao().upsert(user)
		db.userDao().delete(user)

		assertNull(db.userDao().get(user.id).firstOrNull())
		assertTrue(db.userDao().getAll().firstOrNull()?.size == 0)
	}
}
