package com.adiliqbal.finize.data.extensions

import com.adiliqbal.finize.common.system.Logger
import com.adiliqbal.finize.common.util.NotFoundException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal fun CoroutineScope.launchSafeApi(logger: Logger? = null, block: suspend () -> Unit) =
	launch {
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