package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.network.model.ApiUser
import com.adiliqbal.finize.network.service.UserService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.usersDB
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseUserService @Inject constructor(
	private val firestore: FirestoreService,
	private val auth: FirebaseAuth
) : UserService {

	private companion object {
		fun userDoc(userId: String) = usersDB(userId)
	}

	override suspend fun getUser(): ApiUser? {
		val firebaseUser = auth.currentUser?.let {
			ApiUser(
				id = it.uid,
				name = it.displayName ?: "",
				email = it.email ?: "",
				image = it.photoUrl?.path
			)
		}
		return firebaseUser?.let {
			firestore.document(userDoc(it.id)).get().result.toObject(ApiUser::class.java)!!
				.copy(image = it.image)
		}
	}

	override suspend fun updateUser(user: ApiUser) {
		firestore.update(path = userDoc(user.id), doc = user.toJson().decodeJson<JsonObject>()!!)
	}
}
