package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.network.model.ApiPaymentCategory
import com.adiliqbal.finize.network.service.PaymentCategoryService
import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreService.Companion.paymentCategoriesDB
import com.google.firebase.firestore.ktx.toObjects
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebasePaymentCategoryService @Inject constructor(
	private val firestore: FirestoreService
) : PaymentCategoryService {

	override suspend fun getPaymentCategories(): List<ApiPaymentCategory> {
		return firestore.collection(paymentCategoriesDB()).get().result.toObjects()
	}
}