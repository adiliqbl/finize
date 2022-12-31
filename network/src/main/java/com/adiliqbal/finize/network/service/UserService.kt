package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiUser
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

	@GET(value = "users/{id}")
	suspend fun getUser(@Path("id") id: String): ApiUser
}