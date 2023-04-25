package com.adiliqbal.finize.auth.conversions

import com.adiliqbal.finize.common.util.Currencies
import com.adiliqbal.finize.model.Profile
import com.adiliqbal.finize.model.User
import com.google.firebase.auth.FirebaseUser

internal fun FirebaseUser.toUser() = User(
	id = uid,
	name = displayName ?: "",
	email = email ?: "",
	image = photoUrl?.path,
	profile = Profile(Currencies.default)
)