package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.model.User

interface AuthRepository {

    suspend fun register(user: User): User
}
