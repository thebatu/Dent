package com.example.dentrealitytestapp.di

import android.app.Application
import com.example.dentrealitytestapp.data.Repository
import com.example.dentrealitytestapp.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(app: Application): Repository {
        return RepositoryImpl(app)
    }
}