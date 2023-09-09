# Android Koin Startup

This is a template to build an Android app applying good practices and using a clean architecture,
you will see that the code is super decoupled with external frameworks and even with the same
Android framework, this will help you to model your domain purely in Kotlin without generating
external dependencies.

## Screenshot

<p align="center">

  <img wight="280" src="https://github.com/santimattius/android-testing/blob/master/screenshoot/entertainment_app.png?raw=true" alt="App Capture"/>

</p>

## Koin startup
Reducing Startup time with background module loading

You can now declared "lazy" Koin module, to avoid trigger any pre allocation of resources and load them in background with Koin start. This can help avoid to block Android starting process, by passing lazy modules to be laoded in background.

* lazyModule - declare a Lazy Kotlin version of Koin Module
* Module.includes - allow to include lazy Modules
* KoinApplication.lazyModules - load lazy modules in background with coroutines, regarding platform default Dispatchers
* Koin.waitAllStartJobs - wait for start jobs to complete
* Koin.runOnKoinStarted - run block code after start completion

Example:

```kotlin
// Lazy loaded module
val m2 = lazyModule {
    singleOf(::ClassB)
}

val m1 = module {
    singleOf(::ClassA) { bind<IClassA>() }
}

startKoin {
    // sync module loading
    modules(m1)
    // load lazy Modules in background
    lazyModules(m2)
}

val koin = KoinPlatform.getKoin()

// wait for start completetion
koin.waitAllStartJobs()

// or run code after start
koin.runOnKoinStarted { koin ->
    // run after background load complete
}
```

## Content

TheMovieDB API: Check this [documentation](https://www.themoviedb.org/documentation/api).

## Setup

Using local properties for define api key:

```properties
apiKey="{your-api-key}"
```

## Verification

Run check project:

```shell
> ./gradlew check
```

Run tests project:

```shell
> ./gradlew test
```

## Dependencies

Below you will find the libraries used to build the template and according to my criteria the most
used in android development so far.

- **[Retrofit](https://square.github.io/retrofit/)**, networking.
- **[Moshi](https://github.com/square/moshi)**, json parser.
- **[Glide](https://github.com/bumptech/glide)**, with image loader.
- **[Kotlin coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)**.

## References

- [Guide to app architecture](https://developer.android.com/jetpack/guide)
- [Android developers](https://developer.android.com/)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Clean Code](https://blog.cleancoder.com/)
