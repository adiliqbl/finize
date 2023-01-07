package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.BaseApiUser

interface UserService {

	suspend fun getUser(id: String): BaseApiUser?
}