package com.adiliqbal.finize.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID

@Entity(tableName = "users")
data class UserWithProfile(
	@PrimaryKey override val id: ID,
	val name: String,
	val email: String,
	val image: String? = null,
	@Embedded(prefix = "profile_") val profile: ProfileEntity
) : BaseEntity