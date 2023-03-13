package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.extensions.channelFlowWithAwait
import com.adiliqbal.finize.common.extensions.withScope
import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.data.extensions.launchSafeApi
import com.adiliqbal.finize.data.extensions.withoutExceptions
import com.adiliqbal.finize.database.dao.AccountDao
import com.adiliqbal.finize.model.Account
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.service.AccountService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor(
	private val accountDao: AccountDao, private val accountService: AccountService
) : AccountRepository {

	override fun getAccounts(search: String?) = channelFlowWithAwait {
		withScope(Dispatchers.Unconfined) {
			accountDao.getAll().map { it.map { entity -> entity.toModel() } }
				.collect { trySend(it) }
		}
		launchSafeApi {
			accountService.getAccounts().let {
				it.data
					?.map { account -> account.toEntity() }
					?.let { accounts -> accountDao.upsert(accounts) }
			}
		}
	}

	override fun getAccount(id: ID): Flow<Account> =
		accountDao.get(id).withoutExceptions().map { it.toModel() }

	override suspend fun createAccount(account: Account): Account {
		val newAccount = accountService.createAccount(account.toApi())
		return newAccount.toEntity().let {
			accountDao.upsert(it)
			it.toModel()
		}
	}

	override suspend fun updateAccount(account: Account) {
		return account.toApi().let {
			accountService.updateAccount(it)
			val entity = it.toEntity()
			accountDao.upsert(entity)
			entity.toModel()
		}
	}

	override suspend fun deleteAccount(id: ID) {
		accountService.deleteAccount(id)
		accountDao.delete(id)
	}
}