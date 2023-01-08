package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.network.Retrofit
import com.adiliqbal.finize.network.service.notion.NotionAccountService
import com.adiliqbal.finize.network.service.notion.NotionBudgetService
import com.adiliqbal.finize.network.service.notion.NotionTransactionService
import com.adiliqbal.finize.network.service.notion.NotionTransactionTemplateService
import com.adiliqbal.finize.network.service.notion.NotionUserService
import com.adiliqbal.finize.network.source.NotionService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NotionModule {

	@Module
	@InstallIn(SingletonComponent::class)
	object ProviderModule {

		@Provides
		@Singleton
		fun provideNotionService(retrofit: Retrofit) =
			retrofit.notion.create(NotionService::class.java)

		@Provides
		@Singleton
		fun provideUserService(retrofit: Retrofit): NotionUserService =
			retrofit.notion.create(NotionUserService::class.java)
	}

	@Binds
	@Singleton
	fun bindAccountService(service: NotionAccountService): NotionAccountService

	@Binds
	@Singleton
	fun bindBudgetService(service: NotionBudgetService): NotionBudgetService

	@Binds
	@Singleton
	fun bindTransactionService(service: NotionTransactionService): NotionTransactionService

	@Binds
	@Singleton
	fun bindTransactionTemplateService(service: NotionTransactionTemplateService): NotionTransactionTemplateService
}
