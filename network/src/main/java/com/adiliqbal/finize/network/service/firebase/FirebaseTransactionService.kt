package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.model.filter.TransactionsFilter
import com.adiliqbal.finize.network.extensions.filter
import com.adiliqbal.finize.network.extensions.paginate
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.BaseApiTransaction
import com.adiliqbal.finize.network.model.request.PaginationQuery
import com.adiliqbal.finize.network.model.response.PaginatedList
import com.adiliqbal.finize.network.service.TransactionService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.transactionsDB
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import com.google.firebase.firestore.ktx.toObjects
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject

@Singleton
internal class FirebaseTransactionService @Inject constructor(
    private val preferences: AppPreferences,
    private val firestore: FirestoreService
) : TransactionService {

    private companion object {
        fun transactionDoc(userId: String, id: String) = "${transactionsDB(userId)}/$id"
    }

    override suspend fun getTransactions(
        paging: PaginationQuery,
        filter: TransactionsFilter?
    ): PaginatedList<BaseApiTransaction> {
        return firestore
            .collection(transactionsDB(preferences.userId()))
            .filter(filter)
            .paginate(paging)
            .get()
            .result
            .let {
                val result = it.toObjects<ApiTransaction>()
                PaginatedList(result, hasMore = result.size >= paging.pageSize)
            }
    }

    override suspend fun getTransaction(id: ID): BaseApiTransaction {
        return firestore
            .document(transactionDoc(preferences.userId(), id))
            .get()
            .result
            .toObject(ApiTransaction::class.java)!!
    }

    override suspend fun createTransaction(transaction: ApiTransaction): ApiTransaction {
        return firestore
            .create(
                transactionsDB(preferences.userId()),
                transaction.toJson().decodeJson<JsonObject>()!!
            )
            .let { transaction.copy(id = it) }
    }

    override suspend fun updateTransaction(transaction: ApiTransaction) =
        firestore.update(
            path = transactionDoc(preferences.userId(), transaction.id),
            doc = transaction.toJson().decodeJson<JsonObject>()!!
        )

    override suspend fun deleteTransaction(id: ID) =
        firestore.delete(transactionDoc(preferences.userId(), id))
}
