package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.network.model.ApiUser
import com.adiliqbal.finize.network.service.AuthService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.usersDB
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseAuthService @Inject constructor(
	private val service: FirestoreService,
	private val auth: FirebaseAuth
) : AuthService {

	override suspend fun register(user: ApiUser) {
		service.create(usersDB(user.id), user.toJson().decodeJson<JsonObject>()!!)
	}

	override suspend fun logout() = auth.signOut()
}