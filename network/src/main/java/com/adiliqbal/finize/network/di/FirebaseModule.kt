package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.network.service.AuthService
import com.adiliqbal.finize.network.service.firebase.FirebaseAccountService
import com.adiliqbal.finize.network.service.firebase.FirebaseAuthService
import com.adiliqbal.finize.network.service.firebase.FirebaseBudgetService
import com.adiliqbal.finize.network.service.firebase.FirebasePaymentCategoryService
import com.adiliqbal.finize.network.service.firebase.FirebaseTransactionService
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

    @Module
    @InstallIn(SingletonComponent::class)
    object ProviderModule {

        @Provides
        fun provideFirestore() = Firebase.firestore

        @Provides
        fun provideAuth() = Firebase.auth
    }

    @Binds
    @Singleton
    fun bindFirestoreService(service: FirestoreServiceImpl): FirestoreService

    @Binds
    @Singleton
    fun bindAuthService(service: FirebaseAuthService): AuthService

    @Binds
    @Singleton
    fun bindUserService(service: FirebaseAccountService): FirebaseAccountService

    @Binds
    @Singleton
    fun bindAccountService(service: FirebaseAccountService): FirebaseAccountService

    @Binds
    @Singleton
    fun bindBudgetService(service: FirebaseBudgetService): FirebaseBudgetService

    @Binds
    @Singleton
    fun bindTransactionService(service: FirebaseTransactionService): FirebaseTransactionService

    @Binds
    @Singleton
    fun bindPaymentCategoryService(
        service: FirebasePaymentCategoryService
    ): FirebasePaymentCategoryService
}
