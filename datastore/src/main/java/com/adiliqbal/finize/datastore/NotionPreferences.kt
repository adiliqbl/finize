package com.adiliqbal.finize.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotionPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

	private companion object {
		val ACCOUNTS_DB = stringPreferencesKey("ACCOUNTS_DB")
		val BUDGETS_DB = stringPreferencesKey("BUDGETS_DB")
		val TRANSACTIONS_DB = stringPreferencesKey("TRANSACTIONS_DB")
	}

	suspend fun accountsDB(): ID =
		dataStore.data.map { preferences -> preferences[ACCOUNTS_DB] }.first()!!

	suspend fun budgetsDB(): ID =
		dataStore.data.map { preferences -> preferences[BUDGETS_DB] }.first()!!

	suspend fun transactionsDB(): ID =
		dataStore.data.map { preferences -> preferences[TRANSACTIONS_DB] }.first()!!

	suspend fun set(accountsDB: ID, transactionsDB: ID, budgetsDB: ID) {
		dataStore.edit { preferences ->
			preferences[ACCOUNTS_DB] = Json.encodeToString(accountsDB)
			preferences[BUDGETS_DB] = Json.encodeToString(budgetsDB)
			preferences[TRANSACTIONS_DB] = Json.encodeToString(transactionsDB)
		}
	}
}
