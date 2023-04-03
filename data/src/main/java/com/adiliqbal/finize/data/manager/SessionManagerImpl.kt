package com.adiliqbal.finize.data.manager

import android.content.Context
import com.adiliqbal.finize.model.AuthCredentials
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionManagerImpl @Inject constructor(
    private val dataCleaner: DataCleaner,
) : SessionManager {

    override suspend fun login(credentials: AuthCredentials) {}

    override suspend fun logout(context: Context) {
        dataCleaner.clean()
    }
}
