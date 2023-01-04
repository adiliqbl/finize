package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.LocalDate

@Entity(
	tableName = "transactions",
	foreignKeys =
	[
		ForeignKey(
			entity = AccountEntity::class,
			parentColumns = ["id"],
			childColumns = ["toAccount"],
			onDelete = ForeignKey.RESTRICT
		),
		ForeignKey(
			entity = AccountEntity::class,
			parentColumns = ["id"],
			childColumns = ["fromAccount"],
			onDelete = ForeignKey.RESTRICT
		)
	]
)
data class TransactionEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val type: TagEntity? = null,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val budgets: List<ID>? = null,
	val tags: List<TagEntity>? = null,
	val note: String? = null,
	val date: Int = 0
) : BaseEntity