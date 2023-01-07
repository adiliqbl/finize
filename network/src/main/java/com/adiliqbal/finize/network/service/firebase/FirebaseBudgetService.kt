package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiBudget
import com.adiliqbal.finize.network.model.BaseApiBudget
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.service.BudgetService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.budgetsDB
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.serialization.json.JsonObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseBudgetService @Inject constructor(
	private val preferences: AppPreferences,
	private val firestore: FirestoreService
) : BudgetService {

	private companion object {
		fun budgetDoc(userId: String, id: String) = "${budgetsDB(userId)}/${id}"
	}

	override suspend fun getBudgets(): PaginatedList<BaseApiBudget> {
		return firestore.collection(budgetsDB(preferences.userId())).get().result.let {
			PaginatedList(it.toObjects<ApiBudget>())
		}
	}

	override suspend fun createBudget(budget: ApiBudget): ApiBudget {
		return firestore.create(
			budgetsDB(preferences.userId()),
			budget.toJson().decodeJson<JsonObject>()!!
		).let { budget.copy(id = it) }
	}

	override suspend fun updateBudget(budget: ApiBudget) = firestore.update(
		path = budgetDoc(preferences.userId(), budget.id),
		doc = budget.toJson().decodeJson<JsonObject>()!!
	)

	override suspend fun deleteBudget(id: ID) =
		firestore.delete(budgetDoc(preferences.userId(), id))
}