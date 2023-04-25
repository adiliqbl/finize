package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.model.AuthCredentials
import com.adiliqbal.finize.model.User
import com.adiliqbal.finize.network.service.AuthService
import com.adiliqbal.finize.network.service.UserService
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
	private val authService: AuthService,
	private val userService: UserService
) : AuthRepository {

	override suspend fun exists(email: String) = authService.exists(email)

	override suspend fun register(user: User): AuthCredentials {
		val response = authService.register(user.toApi()).toEntity().toModel()
		return AuthCredentials(response)
	}

	override suspend fun loginWithGoogle(id: String): AuthCredentials {
		return AuthCredentials(userService.getUser()!!.toEntity().toModel())
	}

	override suspend fun logout() = authService.logout()
}
