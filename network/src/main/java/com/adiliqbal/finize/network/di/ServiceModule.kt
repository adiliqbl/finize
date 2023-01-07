package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.enums.AuthType
import com.adiliqbal.finize.network.service.UserService
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
		notionUserService: NotionUserService
	): UserService {
		return if (preferences.authType() == AuthType.NOTION) notionUserService
		else notionUserService
	}
}
