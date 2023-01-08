package com.adiliqbal.finize.testing.di

import com.adiliqbal.finize.common.di.DispatchersModule
import com.adiliqbal.finize.common.network.Dispatcher
import com.adiliqbal.finize.common.network.Thread
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher

@Module
@TestInstallIn(
	components = [SingletonComponent::class],
	replaces = [DispatchersModule::class],
)
object TestDispatchersModule {

	@Provides
	@Dispatcher(Thread.IO)
	fun providesIODispatcher(testDispatcher: TestDispatcher): CoroutineDispatcher = testDispatcher
}
