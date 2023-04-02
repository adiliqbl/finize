package com.adiliqbal.finize.database.di

import com.adiliqbal.finize.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideAccountDao(database: AppDatabase) = database.accountDao()

    @Provides
    @Singleton
    fun provideBudgetDao(database: AppDatabase) = database.budgetDao()

    @Provides
    @Singleton
    fun provideTransactionDao(database: AppDatabase) = database.transactionDao()

    @Provides
    @Singleton
    fun provideExchangeRateDao(database: AppDatabase) = database.exchangeRateDao()
}
