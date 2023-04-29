package com.santimattius.template.di

import android.app.Application
import com.santimattius.template.BuildConfig
import com.santimattius.template.data.client.database.AppDataBase
import com.santimattius.template.data.client.network.RetrofitServiceCreator
import com.santimattius.template.data.client.network.TheMovieDBService
import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.data.datasources.implementation.MovieDataSource
import com.santimattius.template.data.datasources.implementation.RoomDataSource
import com.santimattius.template.data.repositories.TMDbRepository
import com.santimattius.template.domain.repositories.MovieRepository
import com.santimattius.template.ui.home.HomeViewModel
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class AppModule {

    @Factory
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): MovieRepository = TMDbRepository(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    @Factory
    fun provideLocalDataSource(appDataBase: AppDataBase): LocalDataSource {
        return RoomDataSource(dao = appDataBase.dao())
    }

    @Single
    fun provideAppDatabase(application: Application): AppDataBase = AppDataBase.get(application)

    @Factory
    fun provideRemoteDataSource(service: TheMovieDBService): RemoteDataSource {
        return MovieDataSource(service = service)
    }

    @Single
    fun provideMovieDBService(): TheMovieDBService =
        RetrofitServiceCreator.create(BuildConfig.API_KEY)


    @KoinViewModel
    fun provideViewModel(movieRepository: MovieRepository):HomeViewModel = HomeViewModel(movieRepository)

}