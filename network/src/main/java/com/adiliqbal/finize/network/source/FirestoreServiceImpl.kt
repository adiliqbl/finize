package com.adiliqbal.finize.network.source

import com.adiliqbal.finize.model.extensions.ID
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirestoreServiceImpl @Inject constructor(private val db: FirebaseFirestore) :
    FirestoreService {

    override suspend fun collection(path: String) = db.collection(path)

    override suspend fun document(path: String) = db.document(path)

    override suspend fun <T : Any> read(path: String, clazz: Class<T>) =
        db.document(path).get().result.toObject(clazz)

    override suspend fun create(path: String, doc: Map<String, Any>): ID {
        return db.collection(path).add(doc).result.let {
            update("$path/${it.id}", mapOf("id" to it.id))
            it.id
        }
    }

    override suspend fun update(path: String, doc: Map<String, Any>) {
        db.document(path).update(doc).result
    }

    override suspend fun delete(path: String) {
        db.document(path).delete().result
    }
}
