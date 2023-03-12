package com.adiliqbal.finize.data.repository

import androidx.paging.PagingData
import com.adiliqbal.finize.model.Transaction
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

	fun getTransactions(filter: TransactionsFilter? = null): Flow<PagingData<Transaction>>

	fun getTransaction(id: ID): Flow<Transaction>

	suspend fun createTransaction(transaction: Transaction): Transaction

	suspend fun updateTransaction(transaction: Transaction)

	suspend fun deleteTransaction(id: ID)


	/**
	 * Templates
	 */
	fun getTemplates(): Flow<List<Transaction>>

	suspend fun createTemplate(template: Transaction)

	suspend fun updateTemplate(template: Transaction)

	suspend fun deleteTemplate(id: ID)
}