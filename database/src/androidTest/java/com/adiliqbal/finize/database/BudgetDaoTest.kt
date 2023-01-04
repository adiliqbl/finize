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
internal class BudgetDaoTest : BaseDatabaseTest() {

	private val budget = FakeEntity.budget()

	@Test
	fun insertBudget() = runTest {
		db.budgetDao().upsert(budget)
		db.budgetDao().upsert(budget)

		val data = db.budgetDao().get(budget.id).first()
		assertEquals(budget.id, data?.id)
	}

	@Test
	fun getBudgets() = runTest {
		db.budgetDao().upsert(budget)
		db.budgetDao().upsert(budget.copy(id = "budget2"))

		val data = db.budgetDao().getAll().firstOrNull()
		assertEquals(2, data?.size)
	}

	@Test
	fun deleteBudget() = runTest {
		db.budgetDao().upsert(budget)
		db.budgetDao().delete(budget)

		assertNull(db.budgetDao().get(budget.id).firstOrNull())
		assertTrue(db.budgetDao().getAll().firstOrNull()?.size == 0)
	}
}
