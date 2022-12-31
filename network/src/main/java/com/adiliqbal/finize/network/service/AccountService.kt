package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiAccount
import com.adiliqbal.finize.network.model.response.PaginatedList

interface AccountService {

	suspend fun getAccounts(): PaginatedList<ApiAccount>
}