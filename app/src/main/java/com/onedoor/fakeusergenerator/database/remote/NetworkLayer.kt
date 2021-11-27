package com.onedoor.fakeusergenerator.database.remote

import com.onedoor.fakeusergenerator.utils.SERVER_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal object NetworkLayer {
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val retrofit = Retrofit.Builder()
        .client((getLoggingHttpClient()))
        .baseUrl(SERVER_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service: Service by lazy {
        retrofit.create(Service::class.java)
    }

    val apiClient = ApiClient(service)

    fun getLoggingHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        return builder.build()
    }
}