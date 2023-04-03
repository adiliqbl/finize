package com.adiliqbal.finize.di

import com.adiliqbal.finize.common.system.NotificationManager
import com.adiliqbal.finize.system.NotificationManagerImpl
import com.adiliqbal.finize.ui.manager.ThemeManager
import com.adiliqbal.finize.system.ThemeManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SystemModule {

	@Binds
	fun bindNotificationManager(manager: NotificationManagerImpl): NotificationManager

	@Binds
	fun bindThemeManager(themeManager: ThemeManagerImpl): ThemeManager
}
