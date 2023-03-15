package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.database.dao.BudgetDao
import com.adiliqbal.finize.database.model.fake.FakeEntity
import com.adiliqbal.finize.model.fake.FakeModel
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.service.BudgetService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.anyList
import org.mockito.kotlin.times

class BudgetRepositoryTest {

	private val dao = Mockito.mock(BudgetDao::class.java)
	private val service = Mockito.mock(BudgetService::class.java)
	private val repository = BudgetRepositoryImpl(dao, service)

	@Test
	fun getBudgets() = runTest {
		Mockito.`when`(dao.getAll()).thenReturn(
			flowOf(
				listOf(
					FakeEntity.budget("one"),
					FakeEntity.budget("two")
				)
			)
		)
		Mockito.`when`(service.getBudgets()).thenReturn(
			PaginatedList(
				listOf(
					FakeModel.budget("three").toApi(),
					FakeModel.budget("four").toApi()
				)
			)
		)

		val budgets = repository.getBudgets().take(1).lastOrNull()
		assertEquals(2, budgets?.size)
		Mockito.verify(dao, times(1)).clear()
		Mockito.verify(dao, times(1)).upsert(anyList())
	}

	@Test
	fun searchBudgets() = runTest {
		Mockito.`when`(dao.getAll()).thenReturn(
			flowOf(
				listOf(
					FakeEntity.budget("one").copy(name = "Budget One"),
					FakeEntity.budget("two").copy(name = "Budget Two")
				)
			)
		)
		Mockito.`when`(service.getBudgets()).thenReturn(PaginatedList(emptyList()))

		val budgets = repository.getBudgets("One").take(1).lastOrNull()
		assertEquals(1, budgets?.size)
		Mockito.verify(dao, times(1)).upsert(anyList())
	}

	@Test
	fun getBudget() = runTest {
		Mockito.`when`(dao.get("one")).thenReturn(
			flowOf(FakeEntity.budget("one"))
		)

		val budget = repository.getBudget("one").first()
		assertEquals("one", budget.id)
	}

	@Test
	fun createBudget() = runTest {
		val api = FakeModel.budget("").toApi()
		Mockito.`when`(service.createBudget(api)).thenReturn(api.copy("id"))

		val budget = repository.createBudget(FakeModel.budget(""))
		assertEquals("id", budget.id)
		Mockito.verify(dao, times(1)).upsert(api.copy("id").toEntity())
	}

	@Test
	fun updateBudget() = runTest {
		val budget = FakeModel.budget().copy(name = "new name")

		repository.updateBudget(budget)
		Mockito.verify(service, times(1)).updateBudget(budget.toApi())
		Mockito.verify(dao, times(1)).upsert(budget.toApi().toEntity())
	}

	@Test
	fun deleteBudget() = runTest {
		repository.deleteBudget("id")
		Mockito.verify(service, times(1)).deleteBudget("id")
		Mockito.verify(dao, times(1)).delete("id")
	}
}
