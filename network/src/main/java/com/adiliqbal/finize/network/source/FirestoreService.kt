package com.adiliqbal.finize.network.source

import com.adiliqbal.finize.model.extensions.ID
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

internal interface FirestoreService {
	companion object {
		fun usersDB(userId: String) = "users/${userId}"
		fun accountsDB(userId: String) = "user-accounts/${userId}/accounts"
		fun budgetsDB(userId: String) = "user-budgets/${userId}/budgets"
		fun transactionsDB(userId: String) = "user-transactions/${userId}/transactions"
		fun transactionTemplatesDB(userId: String) = "user-transactions/${userId}/templates"
	}

	suspend fun collection(path: String): CollectionReference

	suspend fun document(path: String): DocumentReference

	suspend fun <T : Any> read(path: String, clazz: Class<T>): T?

	suspend fun create(path: String, doc: Map<String, Any>): ID

	suspend fun update(path: String, doc: Map<String, Any>)

	suspend fun delete(path: String)
}