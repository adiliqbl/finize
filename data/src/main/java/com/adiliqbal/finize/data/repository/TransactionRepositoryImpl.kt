package com.adiliqbal.finize.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.adiliqbal.finize.common.extensions.withScope
import com.adiliqbal.finize.common.system.Logger
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.data.extensions.launchSafeApi
import com.adiliqbal.finize.data.mediator.TransactionsRemoteMediator
import com.adiliqbal.finize.database.dao.TransactionDao
import com.adiliqbal.finize.model.Transaction
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.service.TransactionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TransactionRepositoryImpl @Inject constructor(
	private val transactionDao: TransactionDao,
	private val transactionService: TransactionService,
	private val logger: Logger
) : TransactionRepository {

	companion object {
		private const val PAGE_SIZE = 30
	}

	@OptIn(ExperimentalPagingApi::class)
	override fun getTransactions(filter: TransactionsFilter?): Flow<PagingData<Transaction>> {
		return Pager(
			config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 0),
			remoteMediator = TransactionsRemoteMediator(
				transactionDao, transactionService, filter, logger
			),
			pagingSourceFactory = { transactionDao.getAll() }
		)
			.flow.map { data -> data.map { it.toModel() } }
	}

	override fun getTransaction(id: ID): Flow<Transaction> {
		withScope(Dispatchers.Unconfined) {
			budgetDao.getAll().map { it.map { entity -> entity.toModel() } }
				.collect { trySend(it) }
		}
		launchSafeApi {
			transactionService.getTransaction(id).let {
				transactionDao.upsert(it.toEntity())
			}
		}
	}

	override suspend fun createTransaction(transaction: Transaction): Transaction {
		TODO("Not yet implemented")
	}

	override suspend fun updateTransaction(transaction: Transaction) {
		TODO("Not yet implemented")
	}

	override suspend fun deleteTransaction(id: ID) {
		TODO("Not yet implemented")
	}

	override fun getTemplates(): Flow<List<Transaction>> {
		TODO("Not yet implemented")
	}

	override suspend fun createTemplate(template: Transaction) {
		TODO("Not yet implemented")
	}

	override suspend fun updateTemplate(template: Transaction) {
		TODO("Not yet implemented")
	}

	override suspend fun deleteTemplate(id: ID) {
		TODO("Not yet implemented")
	}
}