package com.adiliqbal.finize.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.adiliqbal.finize.database.model.UserEntity
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<UserEntity>() {

	@Transaction
	@Query("SELECT * FROM users WHERE id = :id")
	abstract fun get(id: ID): Flow<UserEntity?>

	@Transaction
	@Query("SELECT * FROM users")
	abstract fun getAll(): Flow<List<UserEntity>?>
}
