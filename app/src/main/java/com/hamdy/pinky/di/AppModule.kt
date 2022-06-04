package com.hamdy.pinky.di

import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.data.remote.MakeupApi
import com.hamdy.pinky.data.repository.LoginRepositoryImpl
import com.hamdy.pinky.data.repository.MakeupRepositoryImpl
import com.hamdy.pinky.domain.repository.LoginRepository
import com.hamdy.pinky.domain.repository.MakeupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): MakeupApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MakeupApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: MakeupApi): MakeupRepository {
        return MakeupRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl()
    }
}