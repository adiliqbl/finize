package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.common.util.CurrencyUtil
import com.adiliqbal.finize.database.model.ProfileEntity
import com.adiliqbal.finize.database.model.UserWithProfile
import com.adiliqbal.finize.model.Profile
import com.adiliqbal.finize.model.User
import com.adiliqbal.finize.network.model.ApiProfile
import com.adiliqbal.finize.network.model.ApiUser
import com.adiliqbal.finize.network.model.BaseApiUser

internal fun User.toApi() = ApiUser(id, name, email, image, profile = profile.toApi())

internal fun BaseApiUser.toEntity() =
    UserWithProfile(
        id,
        name,
        email ?: "",
        image,
        profile = profile?.toEntity()
            ?: ProfileEntity(currency = CurrencyUtil.default.currencyCode)
    )

internal fun UserWithProfile.toModel() = User(id, name, email, image, profile = profile.toModel())

internal fun Profile.toApi() = ApiProfile(currency = currency.currencyCode)

internal fun ApiProfile.toEntity() = ProfileEntity(currency = currency)

internal fun ProfileEntity.toModel() = Profile(currency = CurrencyUtil.of(currency))
