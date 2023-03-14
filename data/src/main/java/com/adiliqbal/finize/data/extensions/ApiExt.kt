package com.adiliqbal.finize.data.extensions

import com.adiliqbal.finize.common.system.Logger
import com.adiliqbal.finize.common.util.NotFoundException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal fun CoroutineScope.launchSafeApi(dispatcher: CoroutineDispatcher, logger: Logger? = null, block: suspend () -> Unit) =
    launch(dispatcher) {
        try {
            block()
        } catch (e: NotFoundException) {
            throw e
        } catch (e: Exception) {
            logger?.e(e)
        }
    }

internal fun <T : Any> Flow<T?>.withExceptions() = map {
    it ?: throw NotFoundException(message = "Not Found")
}
