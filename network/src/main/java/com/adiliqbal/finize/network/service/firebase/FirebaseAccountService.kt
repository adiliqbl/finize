package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.BaseApiAccount
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.service.AccountService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.accountsDB
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseAccountService @Inject constructor(
	private val preferences: AppPreferences,
	private val firestore: FirestoreService
) : AccountService {

	private companion object {
		fun accountDoc(userId: String, id: String) = "${accountsDB(userId)}/${id}"
	}

	override suspend fun getAccounts(): PaginatedList<BaseApiAccount> {
		return firestore.collection(accountsDB(preferences.userId())).get().result.let {
			PaginatedList(it.toObjects<ApiAccount>())
		}
	}

	override suspend fun createAccount(account: ApiAccount): ApiAccount {
		return firestore.create(
			accountsDB(preferences.userId()),
			account.toJson().decodeJson<JsonObject>()!!
		).let { account.copy(id = it) }
	}

	override suspend fun updateAccount(account: ApiAccount) {
		firestore.update(
			path = accountDoc(preferences.userId(), account.id),
			doc = account.toJson().decodeJson<JsonObject>()!!
		)
	}

	override suspend fun deleteAccount(id: ID) =
		firestore.delete(accountDoc(preferences.userId(), id))
}