package com.adiliqbal.finize.network

import javax.inject.Inject
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

internal class Retrofit @Inject constructor(client: OkHttpClient, json: Converter.Factory) {

    internal companion object {
        const val NOTION_API = "https://api.notion.com/v1"
    }

    val notion: Retrofit =
        Retrofit.Builder().baseUrl(NOTION_API).client(client).addConverterFactory(json).build()
}
