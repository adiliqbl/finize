package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID

@Entity(
	tableName = "transactions",
	indices = [Index("accountTo"), Index("accountFrom"), Index("budget"), Index("type"), Index("date")],
	foreignKeys =
	[
		ForeignKey(
			entity = AccountEntity::class,
			parentColumns = ["id"],
			childColumns = ["accountTo"],
			onDelete = ForeignKey.RESTRICT
		),
		ForeignKey(
			entity = AccountEntity::class,
			parentColumns = ["id"],
			childColumns = ["accountFrom"],
			onDelete = ForeignKey.RESTRICT
		),
		ForeignKey(
			entity = BudgetEntity::class,
			parentColumns = ["id"],
			childColumns = ["budget"],
			onDelete = ForeignKey.SET_NULL
		)
	]
)
data class TransactionEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val type: TransactionType = TransactionType.UNKNOWN,
	val accountTo: ID? = null,
	val accountFrom: ID? = null,
	val budget: ID? = null,
	val tags: List<String>? = null,
	val note: String? = null,
	val date: Long = 0,
	internal val isTemplate: Boolean = false
) : BaseEntity