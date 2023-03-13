package com.adiliqbal.finize.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.adiliqbal.finize.common.system.Logger
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.extensions.initialPageSize
import com.adiliqbal.finize.database.dao.TransactionDao
import com.adiliqbal.finize.database.model.TransactionEntity
import com.adiliqbal.finize.model.enums.SortOrder
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.request.PaginationQuery
import com.adiliqbal.finize.network.service.TransactionService
import java.io.IOException
import java.net.UnknownHostException

@OptIn(ExperimentalPagingApi::class)
internal class TransactionsRemoteMediator(
	private val transactionDao: TransactionDao,
	private val transactionService: TransactionService,
	private val filter: TransactionsFilter?,
	private val logger: Logger
) : RemoteMediator<Int, TransactionEntity>() {

	private var page: Int = 1

	override suspend fun load(
		loadType: LoadType,
		state: PagingState<Int, TransactionEntity>
	): MediatorResult {
		return try {
			val lastTransactionId = when (loadType) {
				LoadType.REFRESH -> {
					page = 1
					null
				}
				LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
				LoadType.APPEND -> {
					val lastItem =
						state.lastItemOrNull()
							?: return MediatorResult.Success(endOfPaginationReached = true)
					page += 1
					lastItem.id
				}
			}

			val response = transactionService.getTransactions(
				filter = filter,
				paging = PaginationQuery(
					page = page,
					pageSize = lastTransactionId?.let { state.config.pageSize }
						?: state.config.initialPageSize,
					sortOrder = SortOrder.DESCENDING,
					sortField = ApiTransaction.Keys.DATE,
					cursor = lastTransactionId,
				)
			)

			response.data?.map { it.toEntity() }?.let { transactionDao.upsert(it) }

			logger.d("Loading Transactions: $loadType - $lastTransactionId: ${response.data?.size ?: 0}")

			MediatorResult.Success(
				endOfPaginationReached =
				response.data.isNullOrEmpty() || response.data!!.size < state.config.pageSize
			)
		} catch (e: UnknownHostException) {
			MediatorResult.Error(e)
		} catch (e: IOException) {
			MediatorResult.Error(e)
		} catch (e: Exception) {
			logger.e(e)
			MediatorResult.Error(e)
		}
	}

	override suspend fun initialize() = InitializeAction.LAUNCH_INITIAL_REFRESH
}
