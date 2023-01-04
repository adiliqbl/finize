package com.adiliqbal.finize.database.di

import com.adiliqbal.finize.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

	@Provides
	fun provideUserDao(database: AppDatabase) = database.userDao()

	@Provides
	fun provideAccountDao(database: AppDatabase) = database.accountDao()

	@Provides
	fun provideBudgetDao(database: AppDatabase) = database.budgetDao()

	@Provides
	fun provideTransactionDao(database: AppDatabase) = database.transactionDao()
}
