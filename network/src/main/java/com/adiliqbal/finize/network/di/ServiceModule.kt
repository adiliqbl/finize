package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.model.enums.DataProvider
import com.adiliqbal.finize.network.service.AccountService
import com.adiliqbal.finize.network.service.AuthService
import com.adiliqbal.finize.network.service.BudgetService
import com.adiliqbal.finize.network.service.PaymentCategoryService
import com.adiliqbal.finize.network.service.TransactionService
import com.adiliqbal.finize.network.service.TransactionTemplateService
import com.adiliqbal.finize.network.service.UserService
import com.adiliqbal.finize.network.service.firebase.FirebaseAccountService
import com.adiliqbal.finize.network.service.firebase.FirebaseAuthService
import com.adiliqbal.finize.network.service.firebase.FirebaseBudgetService
import com.adiliqbal.finize.network.service.firebase.FirebasePaymentCategoryService
import com.adiliqbal.finize.network.service.firebase.FirebaseTransactionService
import com.adiliqbal.finize.network.service.firebase.FirebaseTransactionTemplateService
import com.adiliqbal.finize.network.service.firebase.FirebaseUserService
import com.adiliqbal.finize.network.service.notion.NotionAccountService
import com.adiliqbal.finize.network.service.notion.NotionBudgetService
import com.adiliqbal.finize.network.service.notion.NotionTransactionService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {

        @Binds
        @Singleton
        fun bindAuthService(service: FirebaseAuthService): AuthService

        @Binds
        @Singleton
        fun bindUserService(service: FirebaseUserService): UserService

        @Binds
        @Singleton
        fun bindTransactionTemplateService(
            service: FirebaseTransactionTemplateService
        ): TransactionTemplateService

        @Binds
        @Singleton
        fun bindPaymentCategoryService(service: FirebasePaymentCategoryService): PaymentCategoryService
    }

    @Provides
    @Singleton
    suspend fun provideAccountService(
        preferences: AppPreferences,
        notion: NotionAccountService,
        firebase: FirebaseAccountService,
    ): AccountService = if (preferences.provider() == DataProvider.NOTION) notion else firebase

    @Provides
    @Singleton
    suspend fun provideBudgetService(
        preferences: AppPreferences,
        notion: NotionBudgetService,
        firebase: FirebaseBudgetService,
    ): BudgetService = if (preferences.provider() == DataProvider.NOTION) notion else firebase

    @Provides
    @Singleton
    suspend fun provideTransactionService(
        preferences: AppPreferences,
        notion: NotionTransactionService,
        firebase: FirebaseTransactionService,
    ): TransactionService = if (preferences.provider() == DataProvider.NOTION) notion else firebase
}
