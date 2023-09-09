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
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule
import org.koin.dsl.module

val appModule = module {
    single(named("api_key")) { BuildConfig.API_KEY }
    single<TheMovieDBService> { RetrofitServiceCreator.create(get(named("api_key"))) }
    single { AppDataBase.get(androidApplication()) }
}

@OptIn(KoinExperimentalAPI::class)
val dataModule = lazyModule {
    single<LocalDataSource> { RoomDataSource(get<AppDataBase>().dao()) }
    single<RemoteDataSource> { MovieDataSource(get()) }
    single<MovieRepository> { TMDbRepository(localDataSource = get(), remoteDataSource = get()) }
}

@OptIn(KoinExperimentalAPI::class)
val uiModule = lazyModule {
    viewModel { HomeViewModel(get()) }
}
