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

class BudgetRepositoryTest {

    private val dispatcher = StandardTestDispatcher()

    private val dao = Mockito.mock(BudgetDao::class.java)
    private val service = Mockito.mock(BudgetService::class.java)
    private val repository = BudgetRepositoryImpl(dao, service, dispatcher)

    @Test
    fun getBudgets() = runTest(dispatcher) {
        whenever(dao.getAll()).thenReturn(
            flowOf(
                listOf(
                    FakeEntity.budget("one"),
                    FakeEntity.budget("two")
                )
            )
        )
        whenever(service.getBudgets()).thenReturn(
            PaginatedList(
                listOf(
                    FakeModel.budget("three").toApi(),
                    FakeModel.budget("four").toApi()
                )
            )
        )
        whenever(dao.transaction(any())).thenAnswer { invocation ->
            val lambda = invocation.getArgument<suspend () -> Unit>(0)
            runBlocking { lambda.invoke() }
        }

        val budgets = repository.getBudgets().take(1).lastOrNull()
        assertEquals(2, budgets?.size)
        Mockito.verify(dao, times(1)).clear()
        Mockito.verify(dao, times(1)).upsert(anyList())
    }

    @Test
    fun searchBudgets() = runTest(dispatcher) {
        whenever(dao.getAll()).thenReturn(
            flowOf(
                listOf(
                    FakeEntity.budget("one").copy(name = "Budget One"),
                    FakeEntity.budget("two").copy(name = "Budget Two")
                )
            )
        )
        whenever(service.getBudgets()).thenReturn(PaginatedList(emptyList()))
        whenever(dao.transaction(any())).thenAnswer { invocation ->
            val lambda = invocation.getArgument<suspend () -> Unit>(0)
            runBlocking { lambda.invoke() }
        }

        val budgets = repository.getBudgets("One").take(1).lastOrNull()
        assertEquals(1, budgets?.size)
        Mockito.verify(dao, times(1)).upsert(anyList())
    }

    @Test
    fun getBudget() = runTest(dispatcher) {
        whenever(dao.get("one")).thenReturn(
            flowOf(FakeEntity.budget("one"))
        )

        val budget = repository.getBudget("one").first()
        assertEquals("one", budget.id)
    }

    @Test
    fun createBudget() = runTest(dispatcher) {
        val api = FakeModel.budget("").toApi()
        whenever(service.createBudget(api)).thenReturn(api.copy("id"))

        val budget = repository.createBudget(FakeModel.budget(""))
        assertEquals("id", budget.id)
        Mockito.verify(dao, times(1)).upsert(api.copy("id").toEntity())
    }

    @Test
    fun updateBudget() = runTest(dispatcher) {
        val budget = FakeModel.budget().copy(name = "new name")

        repository.updateBudget(budget)
        Mockito.verify(service, times(1)).updateBudget(budget.toApi())
        Mockito.verify(dao, times(1)).upsert(budget.toApi().toEntity())
    }

    @Test
    fun deleteBudget() = runTest(dispatcher) {
        repository.deleteBudget("id")
        Mockito.verify(service, times(1)).deleteBudget("id")
        Mockito.verify(dao, times(1)).delete("id")
    }
}
