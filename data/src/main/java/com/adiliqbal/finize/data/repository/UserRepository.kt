package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.model.Profile
import com.adiliqbal.finize.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(): Flow<User>

    suspend fun updateUser(user: User)

    suspend fun updateProfile(profile: Profile)
}
