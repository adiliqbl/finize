package com.adiliqbal.finize.database.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Update
import com.adiliqbal.finize.database.model.UserEntity
import com.adiliqbal.finize.database.model.UserWithProfile
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao : BaseDao<UserWithProfile>() {

	@RewriteQueriesToDropUnusedColumns
	@Query("SELECT * FROM users WHERE id = :id")
	abstract fun get(id: ID): Flow<UserWithProfile?>

	@RewriteQueriesToDropUnusedColumns
	@Query("SELECT * FROM users")
	abstract fun getAll(): Flow<List<UserEntity>?>

	@Update(entity = UserWithProfile::class, onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun update(item: UserEntity)
}
