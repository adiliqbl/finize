package com.adiliqbal.finize.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID

@Entity(tableName = "users")
data class UserEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val image: String? = null,
) : BaseEntity