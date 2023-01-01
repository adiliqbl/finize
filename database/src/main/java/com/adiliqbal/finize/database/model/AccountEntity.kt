package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant

@Entity(tableName = "accounts")
data class AccountEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val currentBalance: Double = 0.0,
	val startingBalance: Double = 0.0,
	val createdAt: Instant? = null
) : BaseEntity