package com.adiliqbal.finize.network.service.firebase

import com.adiliqbal.finize.common.system.Logger
import com.adiliqbal.finize.network.model.ApiExchangeRate
import com.adiliqbal.finize.network.service.ForexService
import com.adiliqbal.finize.network.util.AppJson.decodeJson
import com.google.firebase.functions.FirebaseFunctions
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

@Singleton
internal class FirebaseForexService @Inject constructor(
    private val functions: FirebaseFunctions,
    private val logger: Logger
) : ForexService {

    override suspend fun getExchangeRate(from: String, to: String, refresh: Boolean) =
        suspendCancellableCoroutine { coroutine ->
            functions.getHttpsCallable("GetExchangeRate")
                .call(mapOf("from" to from, "to" to to, "refresh" to refresh))
                .addOnFailureListener {
                    logger.e(it)
                    coroutine.resumeWithException(it)
                }
                .addOnSuccessListener {
                    coroutine.resume(it.data.toString().decodeJson<ApiExchangeRate>()!!)
                }
        }
}
