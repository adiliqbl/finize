package com.adiliqbal.finize.network.extensions

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference

internal inline fun <reified T : Any> CollectionReference.listen(
	crossinline onEvent: (T, isDeleted: Boolean) -> Unit,
	crossinline onError: (Throwable) -> Unit,
) {
	addSnapshotListener { value, error ->
		if (error != null) {
			onError(error)
			return@addSnapshotListener
		}

		value?.documentChanges?.forEach {
			val wasDocumentDeleted = it.type == DocumentChange.Type.REMOVED
			onEvent(it.document.toObject(T::class.java), wasDocumentDeleted)
		}
	}
}

internal inline fun <reified T : Any> DocumentReference.listen(
	crossinline onEvent: (T?) -> Unit,
	crossinline onError: (Throwable) -> Unit,
) {
	addSnapshotListener { value, error ->
		if (error != null) {
			onError(error)
			return@addSnapshotListener
		}

		onEvent(value?.toObject(T::class.java))
	}
}

internal fun generateKeywords(text: String): List<String> {
	val keywords = mutableListOf<String>()
	for (i in text.indices) {
		for (j in (i+1)..text.length) {
			keywords.add(text.slice(i until j))
		}
	}
	return keywords
}