package com.adiliqbal.finize.network.service.notion

import com.adiliqbal.finize.network.model.NotionApiUser
import retrofit2.http.GET
import retrofit2.http.Path

internal interface NotionUserService {

	@GET(value = "/users/{id}")
	suspend fun getUser(@Path("id") id: String): NotionApiUser?
}