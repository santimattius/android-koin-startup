package com.santimattius.template.di

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
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        HomeViewModel(movieRepository = get())
    }

    single {
        AppDataBase.get(androidApplication())
    }

    single<TheMovieDBService> {
        RetrofitServiceCreator.create(BuildConfig.API_KEY)
    }

    factory<MovieRepository> {
        TMDbRepository(
            remoteDataSource = get<RemoteDataSource>(),
            localDataSource = get<LocalDataSource>()
        )
    }

    factory<LocalDataSource> {
        RoomDataSource(dao = get<AppDataBase>().dao())
    }

    factory<RemoteDataSource> {
        MovieDataSource(service = get<TheMovieDBService>())
    }
}