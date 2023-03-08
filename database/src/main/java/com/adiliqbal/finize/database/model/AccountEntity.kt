package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.enums.AccountType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

@Entity(
	tableName = "accounts",
	indices = [Index("budget")],
	foreignKeys =
	[
		ForeignKey(
			entity = BudgetEntity::class,
			parentColumns = ["id"],
			childColumns = ["budget"],
			onDelete = ForeignKey.SET_NULL
		)
	]
)
data class AccountEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val type: AccountType,
	val balance: Double = 0.0,
	val currency: String,
	val budget: ID? = null,
	val createdAt: Instant? = null
) : BaseEntity