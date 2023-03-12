package com.adiliqbal.finize.data.extensions

import com.adiliqbal.finize.common.system.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun CoroutineScope.launchSafeApi(logger: Logger? = null, block: suspend () -> Unit) =
	launch {
		try {
			block()
		} catch (e: Exception) {
			logger?.e(e)
		}
	}