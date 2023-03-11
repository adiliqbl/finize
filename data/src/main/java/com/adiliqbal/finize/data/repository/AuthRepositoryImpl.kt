package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.network.service.AuthService
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
	private val authService: AuthService
) : AuthRepository