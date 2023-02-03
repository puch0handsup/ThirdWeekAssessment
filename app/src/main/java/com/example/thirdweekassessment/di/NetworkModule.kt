package com.example.thirdweekassessment.di

import com.example.thirdweekassessment.rest.SchoolsApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    // provide a moshi object MOshi
    // KotlinJsonadapter moshi

    @Provides
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SchoolsApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun providesOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Change it to NONE when app is launched to production
        }
    }

    @Provides
    fun providesIODispatcher(): CoroutineDispatcher =
        Dispatchers.IO


    @Provides
    fun providesSchoolService(retrofit: Retrofit): SchoolsApi {
        return retrofit.create(SchoolsApi::class.java)
    }
}