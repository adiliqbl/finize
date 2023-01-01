package com.adiliqbal.finize.database

import javax.inject.Inject

interface DatabaseCleaner {

	fun clean()
}

internal class DatabaseCleanerImpl @Inject constructor(private val database: AppDatabase) :
	DatabaseCleaner {

	override fun clean() = database.clearAllTables()
}
