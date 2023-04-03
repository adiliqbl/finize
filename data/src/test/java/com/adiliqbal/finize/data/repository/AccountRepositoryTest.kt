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
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyList
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever

class AccountRepositoryTest {

	private val dispatcher = StandardTestDispatcher()

	private val dao = Mockito.mock(AccountDao::class.java)
	private val service = Mockito.mock(AccountService::class.java)
	private val repository = AccountRepositoryImpl(dao, service, dispatcher)

	@Test
	fun getAccounts() = runTest(dispatcher) {
		whenever(dao.getAll()).thenReturn(
			flowOf(
				listOf(
					FakeEntity.account("one"),
					FakeEntity.account("two")
				)
			)
		)
		whenever(service.getAccounts()).thenReturn(PaginatedList(emptyList()))
		whenever(dao.transaction(any())).thenAnswer { invocation ->
			val lambda = invocation.getArgument<suspend () -> Unit>(0)
			runBlocking { lambda.invoke() }
		}

		val accounts = repository.getAccounts().take(1).lastOrNull()
		assertEquals(2, accounts?.size)
		Mockito.verify(dao, times(1)).clear()
		Mockito.verify(dao, times(1)).upsert(anyList())
	}

	@Test
	fun searchAccounts() = runTest(dispatcher) {
		whenever(dao.getAll()).thenReturn(
			flowOf(
				listOf(
					FakeEntity.account("one").copy(name = "Account One"),
					FakeEntity.account("two").copy(name = "Account Two")
				)
			)
		)
		whenever(service.getAccounts()).thenReturn(PaginatedList(emptyList()))
        whenever(dao.transaction(any())).thenAnswer { invocation ->
            val lambda = invocation.getArgument<suspend () -> Unit>(0)
            runBlocking { lambda.invoke() }
        }

		val accounts = repository.getAccounts("One").take(1).lastOrNull()
		assertEquals(1, accounts?.size)
		Mockito.verify(dao, times(1)).upsert(anyList())
	}

	@Test
	fun getAccount() = runTest(dispatcher) {
		whenever(dao.get("one")).thenReturn(
			flowOf(AccountWithRefs(FakeEntity.account("one")))
		)

		val account = repository.getAccount("one").first()
		assertEquals("one", account.id)
	}

	@Test
	fun createAccount() = runTest(dispatcher) {
		val api = FakeModel.account("").toApi()
		whenever(service.createAccount(api)).thenReturn(api.copy("id"))

		val account = repository.createAccount(FakeModel.account(""))
		assertEquals("id", account.id)
		Mockito.verify(dao, times(1)).upsert(api.copy("id").toEntity())
	}

	@Test
	fun updateAccount() = runTest(dispatcher) {
		val account = FakeModel.account().copy(name = "new name")

		repository.updateAccount(account)
		Mockito.verify(service, times(1)).updateAccount(account.toApi())
		Mockito.verify(dao, times(1)).upsert(account.toApi().toEntity())
	}

	@Test
	fun deleteAccount() = runTest(dispatcher) {
		repository.deleteAccount("id")
		Mockito.verify(service, times(1)).deleteAccount("id")
		Mockito.verify(dao, times(1)).delete("id")
	}
}
