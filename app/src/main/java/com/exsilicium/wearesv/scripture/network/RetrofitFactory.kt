package com.exsilicium.wearesv.scripture.network

import com.exsilicium.wearesv.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// TODO User Dagger
object RetrofitFactory {
    private const val BASE_URL = "https://api.esv.org/v3/"

    fun makePassageService(): PassageService {
        return Retrofit.Builder()
            .client(provideOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(PassageService::class.java)
    }

    private fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().addHeader(
                    "Authorization", "Token ${BuildConfig.ESV_API_KEY}"
                ).build()
            )
        }
        .build()
}
