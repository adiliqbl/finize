package com.adiliqbal.finize.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.Money
import com.adiliqbal.finize.model.extensions.ID

@Entity(
	tableName = "transactions",
	indices = [
		Index("accountTo"),
		Index("accountFrom"),
		Index("budget"),
		Index("categories"),
		Index("date")
	],
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
	@Embedded(prefix = "amount_") val amount: Money,
	@Embedded(prefix = "amount_to_") val amountTo: Money? = null,
	@Embedded(prefix = "amount_from_") val amountFrom: Money? = null,
	@Embedded(prefix = "amount_local_") val amountLocal: Money? = null,
	val accountTo: ID? = null,
	val accountFrom: ID? = null,
	val budget: ID? = null,
	val task: ID? = null,
	val note: String? = null,
	val categories: List<String> = emptyList(),
	val date: Long = 0,
	internal val isTemplate: Boolean = false
) : BaseEntity