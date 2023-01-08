package com.adiliqbal.finize.common.di

import com.adiliqbal.finize.common.network.Dispatcher
import com.adiliqbal.finize.common.network.Thread
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

	@Provides
	@Dispatcher(Thread.IO)
	fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

	@Provides
	@Dispatcher(Thread.MAIN)
	fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
