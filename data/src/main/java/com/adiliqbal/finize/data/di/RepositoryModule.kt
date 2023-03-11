package com.adiliqbal.finize.data.di

import com.adiliqbal.finize.data.repository.AccountRepository
import com.adiliqbal.finize.data.repository.AccountRepositoryImpl
import com.adiliqbal.finize.data.repository.AuthRepository
import com.adiliqbal.finize.data.repository.AuthRepositoryImpl
import com.adiliqbal.finize.data.repository.BudgetRepository
import com.adiliqbal.finize.data.repository.BudgetRepositoryImpl
import com.adiliqbal.finize.data.repository.TransactionRepository
import com.adiliqbal.finize.data.repository.TransactionRepositoryImpl
import com.adiliqbal.finize.data.repository.UserRepository
import com.adiliqbal.finize.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

	@Binds
	fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

	@Binds
	fun bindUserRepository(repository: UserRepositoryImpl): UserRepository

	@Binds
	fun bindAccountRepository(repository: AccountRepositoryImpl): AccountRepository

	@Binds
	fun bindBudgetRepository(repository: BudgetRepositoryImpl): BudgetRepository

	@Binds
	fun bindTransactionRepository(repository: TransactionRepositoryImpl): TransactionRepository
}
