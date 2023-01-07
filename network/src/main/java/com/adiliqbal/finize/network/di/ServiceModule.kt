package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.enums.AuthType
import com.adiliqbal.finize.network.service.AccountService
import com.adiliqbal.finize.network.service.BudgetService
import com.adiliqbal.finize.network.service.TransactionService
import com.adiliqbal.finize.network.service.UserService
import com.adiliqbal.finize.network.service.firebase.FirebaseAccountService
import com.adiliqbal.finize.network.service.firebase.FirebaseBudgetService
import com.adiliqbal.finize.network.service.firebase.FirebaseTransactionService
import com.adiliqbal.finize.network.service.firebase.FirebaseUserService
import com.adiliqbal.finize.network.service.notion.NotionAccountService
import com.adiliqbal.finize.network.service.notion.NotionBudgetService
import com.adiliqbal.finize.network.service.notion.NotionTransactionService
import com.adiliqbal.finize.network.service.notion.NotionUserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

	@Provides
	@Singleton
	suspend fun provideUserService(
		preferences: AppPreferences,
		notion: NotionUserService,
		firebase: FirebaseUserService,
	): UserService = if (preferences.authType() == AuthType.NOTION) notion else firebase

	@Provides
	@Singleton
	suspend fun provideAccountService(
		preferences: AppPreferences,
		notion: NotionAccountService,
		firebase: FirebaseAccountService,
	): AccountService = if (preferences.authType() == AuthType.NOTION) notion else firebase

	@Provides
	@Singleton
	suspend fun provideBudgetService(
		preferences: AppPreferences,
		notion: NotionBudgetService,
		firebase: FirebaseBudgetService,
	): BudgetService = if (preferences.authType() == AuthType.NOTION) notion else firebase

	@Provides
	@Singleton
	suspend fun provideTransactionService(
		preferences: AppPreferences,
		notion: NotionTransactionService,
		firebase: FirebaseTransactionService,
	): TransactionService = if (preferences.authType() == AuthType.NOTION) notion else firebase
}
