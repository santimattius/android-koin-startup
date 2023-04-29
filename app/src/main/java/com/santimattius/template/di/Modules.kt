package com.santimattius.template.di

import com.santimattius.template.BuildConfig
import com.santimattius.template.data.client.database.AppDataBase
import com.santimattius.template.data.client.network.RequestInterceptor
import com.santimattius.template.data.client.network.TheMovieDBService
import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.data.datasources.implementation.MovieDataSource
import com.santimattius.template.data.datasources.implementation.RoomDataSource
import com.santimattius.template.data.repositories.TMDbRepository
import com.santimattius.template.domain.repositories.MovieRepository
import com.santimattius.template.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single {
        AppDataBase.get(androidApplication())
    }

    single<Retrofit>(named("retrofit")) {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(RequestInterceptor(BuildConfig.API_KEY))
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}

val dataModule = module {
    single<TheMovieDBService> {
        get<Retrofit>(named("retrofit")).create()
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

val domainModule = module {
    viewModel {
        HomeViewModel(movieRepository = get())
    }
}