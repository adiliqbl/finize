package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.database.dao.TransactionDao
import com.adiliqbal.finize.network.service.TransactionService
import javax.inject.Inject

internal class TransactionRepositoryImpl @Inject constructor(
	private val transactionDao: TransactionDao,
	private val transactionService: TransactionService
) : TransactionRepository