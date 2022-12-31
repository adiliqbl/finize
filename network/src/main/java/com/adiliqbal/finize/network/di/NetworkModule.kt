package com.adiliqbal.finize.network.di

import com.adiliqbal.finize.datastore.AppPreferences
import com.adiliqbal.finize.network.Retrofit
import com.adiliqbal.finize.network.util.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter

@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

	@Provides
	fun provideAuthInterceptor(preferences: AppPreferences) = AuthInterceptor(preferences)

	@Provides
	fun provideHttpLoggingInterceptor() =
		HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

	@Provides
	fun provideOkHttpClient(
		authInterceptor: AuthInterceptor,
		loggingInterceptor: HttpLoggingInterceptor
	) =
		OkHttpClient.Builder()
			.addInterceptor(authInterceptor)
			.addInterceptor(loggingInterceptor)
			.build()

	@Provides
	fun provideJson() = Json { ignoreUnknownKeys = true }

	@Provides
	fun provideJsonConverter(json: Json): Converter.Factory =
		json.asConverterFactory("application/json".toMediaType())

	@Provides
	fun provideRetrofit(client: OkHttpClient, json: Converter.Factory) = Retrofit(client, json)
}
