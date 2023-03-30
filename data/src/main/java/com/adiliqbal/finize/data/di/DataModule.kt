package com.adiliqbal.finize.data.di

import com.adiliqbal.finize.data.delegates.Preferences
import com.adiliqbal.finize.data.delegates.PreferencesImpl
import com.adiliqbal.finize.data.manager.DataCleaner
import com.adiliqbal.finize.data.manager.DataCleanerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun provideDataCleaner(dataCleaner: DataCleanerImpl): DataCleaner

    @Binds
    fun providePreferences(preferences: PreferencesImpl): Preferences
}
