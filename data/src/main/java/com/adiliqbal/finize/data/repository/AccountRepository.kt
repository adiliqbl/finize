package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.model.Account
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getAccounts(search: String? = null): Flow<List<Account>>

    fun getAccount(id: ID): Flow<Account>

    suspend fun createAccount(account: Account): Account

    suspend fun updateAccount(account: Account)

    suspend fun deleteAccount(id: ID)
}
