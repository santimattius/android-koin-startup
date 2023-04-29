# Android Dependency Injection

This is a template to build an Android app applying good practices and using a clean architecture,
you will see that the code is super decoupled with external frameworks and even with the same
Android framework, this will help you to model your domain purely in Kotlin without generating
external dependencies.

## Screenshot

<p align="center">

  <img wight="280" src="https://github.com/santimattius/android-testing/blob/master/screenshoot/entertainment_app.png?raw=true" alt="App Capture"/>

</p>

## Branchs

- Started: [here](https://github.com/santimattius/android-dependency-injection/tree/di_00)
- IoC: [here](https://github.com/santimattius/android-dependency-injection/tree/di_01)
- Manual DI: [here](https://github.com/santimattius/android-dependency-injection/tree/di_02)
- Hilt:
  -   Using Inject: [here](https://github.com/santimattius/android-dependency-injection/tree/di_03_hilt_inject)
  -   Using Provide: [here](https://github.com/santimattius/android-dependency-injection/tree/di_04_hilt_provide)
- Koin:
  -   Using Inject: [here](https://github.com/santimattius/android-dependency-injection/tree/di_05_koin_inject)
  -   Using module definition: [here](https://github.com/santimattius/android-dependency-injection/tree/di_06_koin_provide)  
  -   Using Koin Annotations: [here](https://github.com/santimattius/android-dependency-injection/tree/di_07_koin_annotations)

## Concepts

### What Is Inversion of Control?
Inversion of Control is a principle in software engineering which transfers the control of objects or portions of a program to a container or framework. We most often use it in the context of object-oriented programming.
In contrast with traditional programming, in which our custom code makes calls to a library, IoC enables a framework to take control of the flow of a program and make calls to our custom code. To enable this, frameworks use abstractions with additional behavior built in. If we want to add our own behavior, we need to extend the classes of the framework or plugin our own classes.
The advantages of this architecture are:
decoupling the execution of a task from its implementation
making it easier to switch between different implementations
greater modularity of a program
greater ease in testing a program by isolating a component or mocking its dependencies, and allowing components to communicate through contracts
We can achieve Inversion of Control through various mechanisms such as: Strategy design pattern, Service Locator pattern, Factory pattern, and Dependency Injection (DI).

### What Is Dependency Injection?
Dependency injection is a pattern we can use to implement IoC, where the control being inverted is setting an object's dependencies.
Connecting objects with other objects, or “injecting” objects into other objects, is done by an assembler rather than by the objects themselves.
Here's how we would create an object dependency in traditional programming:

```java
public class Store {
    private Item item;
 
    public Store() {
        item = new ItemImpl1();    
    }
}

```

In the example above, we need to instantiate an implementation of the Item interface within the Store class itself.

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
