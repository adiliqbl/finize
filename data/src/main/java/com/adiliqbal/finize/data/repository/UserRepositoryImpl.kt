package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.extensions.channelFlowWithAwait
import com.adiliqbal.finize.common.extensions.withScope
import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.data.extensions.launchSafeApi
import com.adiliqbal.finize.database.dao.UserDao
import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.Profile
import com.adiliqbal.finize.model.User
import com.adiliqbal.finize.network.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

internal class UserRepositoryImpl
@Inject
constructor(
    private val userDao: UserDao,
    private val preferences: AppPreferences,
    private val userService: UserService
) : UserRepository {

    override fun getUser() = channelFlowWithAwait {
        val id = preferences.userId()
        withScope(Dispatchers.Unconfined) {
            userDao.get(id).mapNotNull { it?.toModel() }.collect { trySend(it) }
        }
        launchSafeApi(Dispatchers.IO) { userService.getUser(id)?.let { userDao.upsert(it.toEntity()) } }
    }

    override suspend fun updateUser(user: User) {
        return user.toApi().let {
            userService.updateUser(it)
            val entity = it.toEntity()
            userDao.upsert(entity)
            entity.toModel()
        }
    }

    override suspend fun updateProfile(profile: Profile) {
        val user = userDao.get(preferences.userId()).first()!!.toModel().copy(profile = profile).toApi()
        userService.updateUser(user)
        userDao.upsert(user.toEntity())
    }
}
