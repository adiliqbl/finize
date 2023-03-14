package com.adiliqbal.finize.database.model

import com.adiliqbal.finize.model.extensions.ID

data class UserEntity(val id: ID, val name: String, val email: String, val image: String? = null)
