package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.model.User
import com.adiliqbal.finize.network.service.AuthService
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
	private val authService: AuthService
) : AuthRepository {

	override suspend fun register(user: User) =
		authService.register(user.toApi()).toEntity().toModel()
}