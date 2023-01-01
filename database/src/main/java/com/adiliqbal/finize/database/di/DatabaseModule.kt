package com.adiliqbal.finize.database.di

import android.content.Context
import com.adiliqbal.finize.database.DatabaseCleaner
import com.adiliqbal.finize.database.DatabaseCleanerImpl
import com.adiliqbal.finize.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

	@Provides
	@Singleton
	fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.getInstance(context)

	@Provides
	@Singleton
	fun provideDatabaseCleaner(appDatabase: AppDatabase): DatabaseCleaner =
		DatabaseCleanerImpl(appDatabase)
}
