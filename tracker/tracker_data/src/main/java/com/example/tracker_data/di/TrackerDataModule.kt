package com.example.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.example.tracker_data.local.entity.TrackerDataBase
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_data.repository.TrackerRepositoryIm
import com.example.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TrackerDataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor (
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(okHttpClient: OkHttpClient):OpenFoodApi{
        return Retrofit.Builder()
            .baseUrl(OpenFoodApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideTrackerDataBase(application: Application):TrackerDataBase{
        return Room.databaseBuilder(
            application,
            TrackerDataBase::class.java,
            "tracker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrackerRepository(openFoodApi: OpenFoodApi,trackerDataBase: TrackerDataBase):TrackerRepository{
       return TrackerRepositoryIm(
           trackerDao = trackerDataBase.trackerDao,
           openFoodApi = openFoodApi
       )
    }
}