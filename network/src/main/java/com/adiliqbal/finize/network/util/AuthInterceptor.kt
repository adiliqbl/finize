package com.adiliqbal.finize.network.util

import com.adiliqbal.finize.datastore.AppPreferences
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import javax.inject.Inject

internal class AuthInterceptor @Inject constructor(private val preferences: AppPreferences) : Interceptor {

	private var token: String? = null

	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val request: Request = chain.request()
		val requestBuilder: Request.Builder = request.newBuilder()
		if (request.header("NO_AUTH") == null) {
			runBlocking {
				token = token ?: preferences.token()
				if (token != null) {
					requestBuilder.addHeader("Authorization", "Bearer $token")
				}
			}
		}
		return chain.proceed(requestBuilder.build())
	}
}
