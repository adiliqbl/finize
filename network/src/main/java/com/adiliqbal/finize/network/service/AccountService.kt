package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.BaseApiAccount
import com.adiliqbal.finize.network.model.response.PaginatedList

interface AccountService {
	suspend fun getAccounts(): PaginatedList<BaseApiAccount>
	suspend fun createAccount(account: ApiAccount): BaseApiAccount
	suspend fun updateAccount(account: ApiAccount)
	suspend fun deleteAccount(id: ID)
}