package com.adiliqbal.finize.common.util

class NotFoundException(
	override val cause: Throwable? = null,
	override val message: String? = null
) : Exception()
