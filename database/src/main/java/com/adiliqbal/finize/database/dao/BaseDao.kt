package com.adiliqbal.finize.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.adiliqbal.finize.database.model.BaseEntity
import com.adiliqbal.finize.model.extensions.ID

abstract class BaseDao<T : BaseEntity>(private val table: String) {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    internal abstract suspend fun insert(item: T): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    internal abstract suspend fun insert(items: List<T>): List<Long>

    @Transaction @Update(onConflict = OnConflictStrategy.REPLACE) abstract suspend fun update(item: T)

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(items: List<T>)

    @Transaction
    open suspend fun upsert(item: T) {
        if (insert(item) == -1L) update(item)
    }

    @Transaction
    open suspend fun upsert(items: List<T>) {
        insert(items)
            .mapIndexedNotNull { index, rowID -> if (rowID == -1L) items[index] else null }
            .let { update(it) }
    }

    @Delete abstract suspend fun delete(item: T)

    @Delete abstract suspend fun delete(item: List<T>)

    open fun delete(id: ID) = query(SimpleSQLiteQuery("DELETE FROM $table WHERE id = $id"))

    @RawQuery protected abstract fun query(query: SupportSQLiteQuery): Int
}
