package com.adiliqbal.finize.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.adiliqbal.finize.datastore.model.AuthCredentials
import com.adiliqbal.finize.model.enums.AuthType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

	private companion object {
		val USER_ID = stringPreferencesKey("USER_ID")
		val AUTH_CREDENTIALS = stringPreferencesKey("AUTH_CREDENTIALS")
		val AUTH_TYPE = stringPreferencesKey("AUTH_TYPE")
	}

	suspend fun userId() = dataStore.data.map { it[USER_ID] }.first()!!

	suspend fun setUserId(id: ID) {
		dataStore.edit { preferences ->
			preferences[USER_ID] = Json.encodeToString(id)
		}
	}

	suspend fun authType() = AuthType.valueOf(dataStore.data.map { it[AUTH_TYPE] }.first()!!)

	suspend fun setAuthType(type: AuthType) {
		dataStore.edit { preferences -> preferences[AUTH_TYPE] = type.name }
	}

	suspend fun token() = authCredentials()?.accessToken

	private suspend fun authCredentials(): AuthCredentials? {
		val json = dataStore.data.map { it[AUTH_CREDENTIALS] }.firstOrNull() ?: return null
		return Json.decodeFromString<AuthCredentials>(json)
	}

	suspend fun setAuthCredentials(credentials: AuthCredentials) {
		dataStore.edit { preferences ->
			preferences[AUTH_CREDENTIALS] = Json.encodeToString(credentials)
		}
	}
}
