package com.adiliqbal.finize.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.adiliqbal.finize.database.model.TransactionEntity
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TransactionDao : BaseDao<TransactionEntity>() {

	@Transaction
	@Query("SELECT * FROM transactions WHERE id = :id AND isTemplate = 0")
	abstract fun get(id: ID): Flow<TransactionEntity?>

	@Transaction
	@Query("SELECT * FROM transactions WHERE isTemplate = 0 ORDER BY date DESC")
	abstract fun getAll(): PagingSource<Int, TransactionEntity>

	@RawQuery(observedEntities = [TransactionEntity::class])
	protected abstract fun query(query: SupportSQLiteQuery): PagingSource<Int, TransactionEntity>

	@Transaction
	@Query("SELECT * FROM transactions WHERE isTemplate = 1 ORDER BY date DESC")
	abstract fun getTemplates(): PagingSource<Int, TransactionEntity>

	open fun filter(filter: TransactionsFilter): PagingSource<Int, TransactionEntity> =
		with(filter) {
			val filters = mutableListOf<String>()

			if (!accountTo.isNullOrEmpty() && accountTo == accountFrom) {
				filters.add("accountFrom='$accountTo' OR accountTo='$accountTo'")
			} else if (!accountTo.isNullOrEmpty() && !accountFrom.isNullOrEmpty()) {
				filters.add("accountFrom='$accountFrom' AND accountTo='$accountTo'")
			} else if (!accountTo.isNullOrEmpty() && accountFrom.isNullOrEmpty()) {
				filters.add("accountTo='$accountTo'")
			} else if (!accountFrom.isNullOrEmpty() && accountTo.isNullOrEmpty()) {
				filters.add("accountFrom='$accountFrom'")
			}

			if (category != null) {
				filters.add("type LIKE '%'||'${category!!.value}'||'%'")
			}
			if (budget != null) {
				filters.add("budget LIKE '%'||'$budget'||'%'")
			}

			if (date != null) {
				filters.add("date=${date!!.toEpochMilliseconds()}")
			} else if (dateFrom != null && dateTo != null) {
				filters.add("date>=${dateFrom!!.toEpochMilliseconds()} AND date<=${dateTo!!.toEpochMilliseconds()}")
			} else if (dateFrom != null) {
				filters.add("date>=${dateFrom!!.toEpochMilliseconds()}")
			} else if (dateTo != null) {
				filters.add("date<=${dateTo!!.toEpochMilliseconds()}")
			}

			if (!name.isNullOrEmpty()) {
				filters.add("name LIKE '%'||'$name'||'%'")
			}

			if (!tags.isNullOrEmpty()) {
				tags!!.forEach { tag -> filters.add("tags LIKE '%'||'$tag'||'%'") }
			}

			if (filters.isEmpty()) getAll()
			else query(
				SimpleSQLiteQuery(
					"""
					SELECT * FROM transactions
					WHERE isTemplate = 0
					AND
					${if (filters.size == 1) filters[0] else filters.joinToString("\n AND ")}
					ORDER BY date DESC
					""".trimIndent()
				)
			)
		}
}
