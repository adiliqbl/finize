package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.database.dao.AccountDao
import com.adiliqbal.finize.database.model.AccountWithRefs
import com.adiliqbal.finize.database.model.fake.FakeEntity
import com.adiliqbal.finize.model.fake.FakeModel
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.service.AccountService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyList
import org.mockito.kotlin.any
import org.mockito.kotlin.times

class AccountRepositoryTest {

	private val dao = Mockito.mock(AccountDao::class.java)
	private val service = Mockito.mock(AccountService::class.java)
	private val repository = AccountRepositoryImpl(dao, service)

	@Test
	fun getAccounts() = runTest {
		Mockito.`when`(dao.getAll()).thenReturn(
			flowOf(
				listOf(
					FakeEntity.account("one"),
					FakeEntity.account("two")
				)
			)
		)
		Mockito.`when`(service.getAccounts()).thenReturn(
			PaginatedList(
				listOf(
					FakeModel.account("three").toApi(),
					FakeModel.account("four").toApi()
				)
			)
		)

		val accounts = repository.getAccounts().take(1).lastOrNull()
		assertEquals(2, accounts?.size)
		Mockito.verify(dao, times(1)).upsert(anyList())
	}

	@Test
	fun getAccount() = runTest {
		Mockito.`when`(dao.get(any())).thenReturn(
			flowOf(AccountWithRefs(FakeEntity.account("one")))
		)

		val account = repository.getAccount("one").first()
		assertEquals("one", account.id)
	}

	@Test
	fun createAccount() = runTest {
		val api = FakeModel.account("").toApi()
		Mockito.`when`(service.createAccount(api)).thenReturn(api.copy("id"))

		val account = repository.createAccount(FakeModel.account(""))
		assertEquals("id", account.id)
		Mockito.verify(dao, times(1)).upsert(api.copy("id").toEntity())
	}

	@Test
	fun updateAccount() = runTest {
		val account = FakeModel.account().copy(name = "new name")

		repository.updateAccount(account)
		Mockito.verify(service, times(1)).updateAccount(account.toApi())
		Mockito.verify(dao, times(1)).upsert(account.toApi().toEntity())
	}

	@Test
	fun deleteAccount() = runTest {
		repository.deleteAccount("id")
		Mockito.verify(service, times(1)).deleteAccount("id")
		Mockito.verify(dao, times(1)).delete("id")
	}
}
