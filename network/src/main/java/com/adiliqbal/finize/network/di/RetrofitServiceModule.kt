package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.network.Retrofit
import com.adiliqbal.finize.network.service.NotionService
import com.adiliqbal.finize.network.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitServiceModule {

	@Provides
	fun provideUserService(retrofit: Retrofit) = retrofit.notion.create(UserService::class.java)

	@Provides
	fun provideNotionService(retrofit: Retrofit) = retrofit.notion.create(NotionService::class.java)
}
