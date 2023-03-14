package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.ApiTransaction
import com.adiliqbal.finize.network.model.BaseApiTransaction
import com.adiliqbal.finize.network.service.TransactionTemplateService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.transactionTemplatesDB
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.adiliqbal.finize.network.util.AppJson.toJson
import com.google.firebase.firestore.ktx.toObjects
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject

@Singleton
internal class FirebaseTransactionTemplateService
@Inject
constructor(private val preferences: AppPreferences, private val firestore: FirestoreService) :
    TransactionTemplateService {

    private companion object {
        fun transactionTemplateDoc(userId: String, id: String) = "${transactionTemplatesDB(userId)}/$id"
    }

    override suspend fun getTemplates(): List<BaseApiTransaction> {
        return firestore
            .collection(transactionTemplatesDB(preferences.userId()))
            .get()
            .result
            .toObjects<ApiTransaction>()
    }

    override suspend fun createTemplate(template: ApiTransaction): BaseApiTransaction {
        return firestore
            .create(
                transactionTemplatesDB(preferences.userId()),
                template.toJson().decodeJson<JsonObject>()!!
            )
            .let { template.copy(id = it) }
    }

    override suspend fun updateTemplate(template: ApiTransaction) =
        firestore.update(
            path = transactionTemplateDoc(preferences.userId(), template.id),
            doc = template.toJson().decodeJson<JsonObject>()!!
        )

    override suspend fun deleteTemplate(id: ID) =
        firestore.delete(transactionTemplateDoc(preferences.userId(), id))
}
