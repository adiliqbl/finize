package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.extensions.ID

data class User(
    val id: ID,
    val name: String,
    val email: String,
    val image: String? = null,
    val profile: Profile
)
