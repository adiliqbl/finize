package com.adiliqbal.finize.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.datastore.DatastoreCleaner
import com.adiliqbal.finize.datastore.NotionPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
internal object DatastoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            migrations = listOf(),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile("finize_preferences") }
        )
    }

    @Provides
    fun provideDatastoreCleaner(dataStore: DataStore<Preferences>) = DatastoreCleaner(dataStore)

    @Provides
    @Singleton
    fun provideAppPreferences(dataStore: DataStore<Preferences>) = AppPreferences(dataStore)

    @Provides
    @Singleton
    fun provideNotionPreferences(dataStore: DataStore<Preferences>) = NotionPreferences(dataStore)
}
