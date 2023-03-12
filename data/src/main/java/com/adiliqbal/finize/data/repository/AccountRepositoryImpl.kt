package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.extensions.channelFlowWithAwait
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.data.extensions.launchSafeApi
import com.adiliqbal.finize.database.dao.AccountDao
import com.adiliqbal.finize.model.Account
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.service.AccountService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor(
	private val accountDao: AccountDao, private val accountService: AccountService
) : AccountRepository {

	override fun getAccounts(search: String?): Flow<List<Account>> {
		TODO("Not yet implemented")
	}

	override fun getAccount(id: ID) = channelFlowWithAwait {
		launch(Dispatchers.Unconfined) {
			accountDao.get(id).mapNotNull { it?.toModel() }.collect { trySend(it) }
		}
		launchSafeApi {
			channelService.getChannel(id).let { channelDao.upsert(it.toEntity(userId)) }
		}
	}

	override suspend fun createAccount(account: Account): Account {
		TODO("Not yet implemented")
	}

	override suspend fun updateAccount(account: Account) {
		TODO("Not yet implemented")
	}

	override suspend fun deleteAccount(id: ID) {
		TODO("Not yet implemented")
	}
}