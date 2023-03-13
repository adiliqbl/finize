package com.adiliqbal.finize.data.manager

import android.content.Context
import com.adiliqbal.finize.datastore.model.AuthCredentials

interface SessionManager {
	suspend fun login(credentials: AuthCredentials)
	suspend fun logout(context: Context)

	suspend fun onResume(credentials: AuthCredentials)
}