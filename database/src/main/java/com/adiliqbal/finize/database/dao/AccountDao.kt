package com.adiliqbal.finize.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adiliqbal.finize.database.model.AccountEntity
import com.adiliqbal.finize.database.model.AccountWithRefs
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AccountDao : BaseDao<AccountEntity>() {

	@Transaction
	@Query("SELECT * FROM accounts WHERE id = :id")
	abstract fun get(id: ID): Flow<AccountWithRefs?>

	@Transaction
	@Query("SELECT * FROM accounts ORDER BY name")
	abstract fun getAll(): Flow<List<AccountEntity>?>
}
