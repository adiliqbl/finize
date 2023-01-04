package com.adiliqbal.finize.database.util

import androidx.paging.PagingSource

suspend fun <T : Any> PagingSource<Int, T>.result() =
	load(PagingSource.LoadParams.Refresh(null, 100, false)).run {
		(this as? PagingSource.LoadResult.Page)?.data
	}
