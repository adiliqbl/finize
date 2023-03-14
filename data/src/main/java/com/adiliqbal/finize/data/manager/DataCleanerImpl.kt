package com.adiliqbal.finize.data.manager

import com.adiliqbal.finize.database.DatabaseCleaner
import com.adiliqbal.finize.datastore.DatastoreCleaner
import javax.inject.Inject

internal class DataCleanerImpl
@Inject
constructor(private val database: DatabaseCleaner, private val datastore: DatastoreCleaner) :
    DataCleaner {

    override suspend fun clean() {
        database.clean()
        datastore.clean()
    }
}
