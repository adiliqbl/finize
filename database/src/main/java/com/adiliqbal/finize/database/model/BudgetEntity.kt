package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

@Entity(tableName = "budgets")
data class BudgetEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val spent: Double = 0.0,
	val maximum: Double = 0.0,
	val createdAt: Instant? = null
) : BaseEntity