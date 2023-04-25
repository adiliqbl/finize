package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiUser
import com.adiliqbal.finize.network.model.BaseApiUser

interface AuthService {
	suspend fun exists(email: String): Boolean
	suspend fun register(user: ApiUser): BaseApiUser
	suspend fun logout()
}
