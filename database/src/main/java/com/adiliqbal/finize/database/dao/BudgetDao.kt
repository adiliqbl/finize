package com.adiliqbal.finize.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adiliqbal.finize.database.model.BudgetEntity
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BudgetDao : BaseDao<BudgetEntity>() {

	@Transaction
	@Query("SELECT * FROM budgets WHERE id = :id")
	abstract fun get(id: ID): Flow<BudgetEntity?>

	@Transaction
	@Query("SELECT * FROM budgets ORDER BY name")
	abstract fun getAll(): Flow<List<BudgetEntity>?>
}
