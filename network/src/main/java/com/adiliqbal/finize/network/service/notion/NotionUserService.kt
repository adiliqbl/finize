package com.adiliqbal.finize.network.service.notion

import com.adiliqbal.finize.network.model.NotionApiUser
import com.adiliqbal.finize.network.service.UserService
import retrofit2.http.GET
import retrofit2.http.Path

internal interface NotionUserService : UserService {

	@GET(value = "/users/{id}")
	override suspend fun getUser(@Path("id") id: String): NotionApiUser?
}