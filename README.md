# Android technical test using the Reddit API

Android app in Kotlin that utilises the [Reddit API](https://www.reddit.com/dev/api/) to display a list of posts from the Android subreddit.

## Technologies used:
* Android SDK
* Kotlin
* View Binding
* Coroutines
* LiveData
* ViewModels
* Repository Pattern
* [Retrofit](https://github.com/square/retrofit)
* [Dagger Hilt](https://dagger.dev/hilt/) - Dependency Injection
* [Timber](https://github.com/JakeWharton/timber) - Improved logging


## Build instructions:
* Clone the repository
* From Android Studio:
    * Open project from the folder you cloned it into.
    * From the menu select **Run** and then **Run 'App'**.
* From the command line:
    * MacOs/Linux:
        `./gradlew installDebug`
    * Windows:
        `gradlew installDebug`