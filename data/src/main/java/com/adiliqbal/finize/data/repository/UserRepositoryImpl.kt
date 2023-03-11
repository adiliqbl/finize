package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.database.dao.UserDao
import com.adiliqbal.finize.network.service.UserService
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
	private val userDao: UserDao,
	private val userService: UserService
) : UserRepository