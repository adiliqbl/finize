package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.network.model.ApiUser

interface FirebaseAuthService {

	suspend fun register(user: ApiUser)

	suspend fun logout()
}