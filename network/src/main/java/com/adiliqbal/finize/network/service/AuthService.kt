package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiUser

interface AuthService {

	suspend fun getUser(): ApiUser?

	suspend fun register(user: ApiUser)

	suspend fun logout()
}