package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.network.model.ApiUser
import com.adiliqbal.finize.network.service.UserService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.usersDB
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseUserService @Inject constructor(
	private val firestore: FirestoreService,
	private val auth: FirebaseAuth
) : UserService {

	private companion object {
		fun userDoc(userId: String) = "${usersDB()}/$userId"
	}

	override suspend fun getUser(id: String): ApiUser? {
		return auth.currentUser?.let {
			var user = firestore.document(userDoc(id)).get().result
				.toObject(ApiUser::class.java)!!
			if (user.image == null) user = user.copy(image = it.photoUrl?.path)
			user
		}
	}
}