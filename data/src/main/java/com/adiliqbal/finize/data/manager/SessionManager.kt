package com.adiliqbal.finize.data.manager

import android.content.Context
import com.adiliqbal.finize.model.AuthCredentials

interface SessionManager {
    suspend fun login(credentials: AuthCredentials)
    suspend fun logout(context: Context)
}
