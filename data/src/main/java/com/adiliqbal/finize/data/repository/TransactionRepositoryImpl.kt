package com.adiliqbal.finize.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.adiliqbal.finize.common.extensions.channelFlowWithAwait
import com.adiliqbal.finize.common.extensions.withScope
import com.adiliqbal.finize.common.system.Logger
import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.data.conversion.toModel
import com.adiliqbal.finize.data.extensions.launchSafeApi
import com.adiliqbal.finize.data.extensions.withExceptions
import com.adiliqbal.finize.data.mediator.TransactionsRemoteMediator
import com.adiliqbal.finize.database.dao.TransactionDao
import com.adiliqbal.finize.model.Transaction
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.service.TransactionService
import com.adiliqbal.finize.network.service.TransactionTemplateService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val transactionService: TransactionService,
    private val templateService: TransactionTemplateService,
    private val logger: Logger
) : TransactionRepository {

    companion object {
        private const val PAGE_SIZE = 30
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTransactions(filter: TransactionsFilter?): Flow<PagingData<Transaction>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 0),
            remoteMediator =
            TransactionsRemoteMediator(transactionDao, transactionService, filter, logger),
            pagingSourceFactory = { transactionDao.getAll() }
        )
            .flow
            .map { data -> data.map { it.toModel() } }
    }

    override fun getTransaction(id: ID) = channelFlowWithAwait {
        withScope(Dispatchers.Unconfined) {
            transactionDao.get(id).withExceptions().collect { trySend(it.toModel()) }
        }
        launchSafeApi(Dispatchers.IO) {
            transactionService.getTransaction(id).let { transactionDao.upsert(it.toEntity()) }
        }
    }

    override suspend fun createTransaction(transaction: Transaction): Transaction {
        return transactionService.createTransaction(transaction.toApi()).toEntity().let {
            transactionDao.upsert(it)
            it.toModel()
        }
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        return transaction.toApi().let {
            transactionService.updateTransaction(it)
            val entity = it.toEntity()
            transactionDao.upsert(entity)
        }
    }

    override suspend fun deleteTransaction(id: ID) {
        transactionService.deleteTransaction(id)
        transactionDao.delete(id)
    }

    override fun getTemplates() = channelFlowWithAwait {
        withScope(Dispatchers.Unconfined) {
            transactionDao
                .getTemplates()
                .map { it.map { entity -> entity.toModel() } }
                .collect { trySend(it) }
        }
        launchSafeApi(Dispatchers.IO) {
            templateService
                .getTemplates()
                .map { it.toEntity().copy(isTemplate = true) }
                .let {
                    transactionDao.clearTemplates()
                    transactionDao.upsert(it)
                }
        }
    }

    override fun getTemplate(id: ID) = getTransaction(id)

    override suspend fun createTemplate(template: Transaction): Transaction {
        return templateService.createTemplate(template.toApi()).let {
            val entity = it.toEntity().copy(isTemplate = true)
            transactionDao.upsert(entity)
            entity.toModel()
        }
    }

    override suspend fun updateTemplate(template: Transaction) {
        return template.toApi().let {
            templateService.updateTemplate(it)
            val entity = it.toEntity()
            transactionDao.upsert(entity.copy(isTemplate = true))
        }
    }

    override suspend fun deleteTemplate(id: ID) {
        templateService.deleteTemplate(id)
        transactionDao.delete(id)
    }
}
