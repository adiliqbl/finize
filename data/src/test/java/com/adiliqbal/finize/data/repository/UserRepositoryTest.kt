package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.util.Currencies
import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.database.dao.UserDao
import com.adiliqbal.finize.database.model.fake.FakeEntity
import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.fake.FakeModel
import com.adiliqbal.finize.network.service.UserService
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times

class UserRepositoryTest {

	private val dao = Mockito.mock(UserDao::class.java)
	private val service = Mockito.mock(UserService::class.java)
	private val preferences = Mockito.mock(AppPreferences::class.java)
	private val repository = UserRepositoryImpl(dao, service, preferences)

	@Test
	fun getUser() = runTest {
		val entity = FakeEntity.user("id")
		val api = FakeModel.user("id").toApi()

		Mockito.`when`(preferences.userId()).thenReturn("id")
		Mockito.`when`(dao.get("id")).thenReturn(flowOf(entity))
		Mockito.`when`(service.getUser("id")).thenReturn(api)

		val user = repository.getUser().take(1).lastOrNull()
		assertEquals(entity.toModel(), user)
		Mockito.verify(dao, times(1)).upsert(api.toEntity())
	}

	@Test
	fun updateUser() = runTest {
		val user = FakeModel.user("id")

		repository.updateUser(user)
		Mockito.verify(service, times(1)).updateUser(user.toApi())
		Mockito.verify(dao, times(1)).upsert(user.toApi().toEntity())
	}

	@Test
	fun updateProfile() = runTest {
		val user = FakeModel.user("id")
		val profile = user.profile.copy(currency = Currencies.of("USD"))

		Mockito.`when`(preferences.userId()).thenReturn("id")
		Mockito.`when`(dao.get("id")).thenReturn(flowOf(user.toApi().toEntity()))

		repository.updateProfile(profile)

		val newUser = user.copy(profile = profile)
		Mockito.verify(service, times(1)).updateUser(newUser.toApi())
		Mockito.verify(dao, times(1)).upsert(newUser.toApi().toEntity())
	}
}
