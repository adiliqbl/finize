package com.adiliqbal.finize.common.util

inline fun <T> tryElse(default: T? = null, block: () -> T): T? {
	return try {
		return block()
	} catch (_: Exception) {
		return default
	}
}