package com.app.rocket.feature.games.di.module


import com.app.rocket.BuildConfig
import com.app.rocket.feature.games.data.api.GamesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GamesModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.GAMES_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideGamesService(retrofit: Retrofit): GamesService =
        retrofit.create(GamesService::class.java)

//    @Provides
//    @Singleton
//    fun provideGamesContract(covidTrackingImpl: CovidTrackingImpl): CovidTrackingContract =
//        covidTrackingImpl

}
