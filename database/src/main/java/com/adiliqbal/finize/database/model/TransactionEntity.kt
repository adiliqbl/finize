package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate

@Entity(tableName = "transactions")
data class TransactionEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val type: TagEntity? = null,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val budgets: List<ID>? = null,
	val tags: List<TagEntity>? = null,
	val note: String? = null,
	val date: LocalDate
) : BaseEntity