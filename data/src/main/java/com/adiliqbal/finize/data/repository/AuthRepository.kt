package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.model.AuthCredentials
import com.adiliqbal.finize.model.User

interface AuthRepository {
	suspend fun exists(email: String): Boolean
	suspend fun register(user: User): AuthCredentials
	suspend fun loginWithGoogle(id: String): AuthCredentials
	suspend fun logout()
}
