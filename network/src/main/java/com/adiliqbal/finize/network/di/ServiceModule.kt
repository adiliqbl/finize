package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.network.service.AccountService
import com.adiliqbal.finize.network.service.AccountServiceImpl
import com.adiliqbal.finize.network.service.BudgetService
import com.adiliqbal.finize.network.service.BudgetServiceImpl
import com.adiliqbal.finize.network.service.TransactionService
import com.adiliqbal.finize.network.service.TransactionServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ServiceModule {

	@Binds
	fun provideAccountService(service: AccountServiceImpl): AccountService

	@Binds
	fun provideBudgetService(service: BudgetServiceImpl): BudgetService

	@Binds
	fun provideTransactionService(service: TransactionServiceImpl): TransactionService
}
