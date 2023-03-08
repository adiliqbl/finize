package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.common.util.CurrencyUtil
import com.adiliqbal.finize.database.model.ProfileEntity
import com.adiliqbal.finize.database.model.UserWithProfile
import com.adiliqbal.finize.model.Profile
import com.adiliqbal.finize.model.User
import com.adiliqbal.finize.network.model.ApiProfile
import com.adiliqbal.finize.network.model.ApiUser

internal fun ApiUser.toEntity() = UserWithProfile(
	id, name, email, image, profile = profile.toEntity()
)

internal fun ApiProfile.toEntity() = ProfileEntity(
	currency = currency
)

internal fun UserWithProfile.toModel() = User(
	id, name, email, image, profile = profile.toModel()
)

internal fun ProfileEntity.toModel() = Profile(
	currency = CurrencyUtil.of(currency)
)