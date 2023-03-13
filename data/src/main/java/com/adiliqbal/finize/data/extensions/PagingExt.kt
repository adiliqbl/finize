package com.adiliqbal.finize.data.extensions

import androidx.paging.PagingConfig

internal val PagingConfig.initialPageSize
	get() = initialLoadSize.coerceAtLeast(pageSize)
