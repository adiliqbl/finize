package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.network.source.FirestoreService
import com.adiliqbal.finize.network.source.FirestoreServiceImpl
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface FirebaseModule {

	@Binds
	@Singleton
	fun provideFirestoreService(service: FirestoreServiceImpl): FirestoreService

	@Module
	@InstallIn(SingletonComponent::class)
	object ProviderModule {

		@Provides
		fun provideFirestore() = Firebase.firestore

		@Provides
		fun provideAuth() = Firebase.auth
	}
}
