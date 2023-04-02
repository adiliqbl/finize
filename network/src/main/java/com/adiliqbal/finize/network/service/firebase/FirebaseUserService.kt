package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.network.model.ApiUser
import com.adiliqbal.finize.network.service.UserService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.usersDB
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject

@Singleton
internal class FirebaseUserService @Inject constructor(
    private val firestore: FirestoreService,
    private val authService: FirebaseAuthService
) : UserService {

    private companion object {
        fun userDoc(userId: String) = usersDB(userId)
    }

    override suspend fun getUser(id: String): ApiUser? {
        return authService.getUser()?.let {
            val user = firestore.document(userDoc(id)).get().result.toObject(ApiUser::class.java)!!
            user.copy(image = it.image)
        }
    }

    override suspend fun updateUser(user: ApiUser) {
        firestore.update(path = userDoc(user.id), doc = user.toJson().decodeJson<JsonObject>()!!)
    }
}
