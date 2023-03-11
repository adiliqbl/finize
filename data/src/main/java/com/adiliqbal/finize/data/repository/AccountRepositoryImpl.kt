package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.database.dao.AccountDao
import com.adiliqbal.finize.network.service.AccountService
import javax.inject.Inject

internal class AccountRepositoryImpl @Inject constructor(
	private val accountDao: AccountDao,
	private val accountService: AccountService
) : AccountRepository