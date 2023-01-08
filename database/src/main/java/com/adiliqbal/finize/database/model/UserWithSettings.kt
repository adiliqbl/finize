package com.adiliqbal.finize.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adiliqbal.finize.model.extensions.ID

@Entity(tableName = "users")
data class UserWithSettingsEntity(
	@PrimaryKey override val id: ID,
	val name: String,
	val image: String? = null,
	@Embedded(prefix = "settings_") val settings: SettingsEntity
) : BaseEntity