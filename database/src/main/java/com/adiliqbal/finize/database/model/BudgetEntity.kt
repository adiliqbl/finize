package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

@Entity(tableName = "budgets")
data class BudgetEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val limit: Double = 0.0,
	val expireAt: LocalDate? = null,
	val createdAt: Instant? = null
) : BaseEntity