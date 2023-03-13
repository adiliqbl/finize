package com.adiliqbal.finize.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.adiliqbal.finize.model.enums.DataProvider
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

	private companion object {
		val USER_ID = stringPreferencesKey("USER_ID")
		val DATA_PROVIDER = stringPreferencesKey("DATA_PROVIDER")
	}

	suspend fun userId() = dataStore.data.map { it[USER_ID] }.first()!!

	suspend fun setUserId(id: ID) {
		dataStore.edit { it[USER_ID] = Json.encodeToString(id) }
	}

	suspend fun provider(): DataProvider = dataStore.data.map { it[USER_ID] }.first()?.let {
		DataProvider.valueOf(it)
	} ?: DataProvider.FINIZE

	suspend fun setProvider(provider: DataProvider) {
		dataStore.edit { it[DATA_PROVIDER] = provider.name }
	}
}
