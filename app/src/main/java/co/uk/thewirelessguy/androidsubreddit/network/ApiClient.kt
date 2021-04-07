package co.uk.thewirelessguy.androidsubreddit.network

import co.uk.thewirelessguy.androidsubreddit.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import zerobranch.androidremotedebugger.logging.NetLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    private const val BASE_URL = "https://www.reddit.com/r/Android/"

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun getClient(): ApiInterface {

        // OkHttpClient for building http request url:
        val client = OkHttpClient.Builder()

        /**
         * Android Remote Debugger: Inspect network calls made by the app using your web browser.
         * https://github.com/zerobranch/android-remote-debugger
         **/
        if (BuildConfig.DEBUG) client.addNetworkInterceptor(NetLoggingInterceptor())

        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory(contentType))
            .client(client.build())
            .build().create(ApiInterface::class.java)
    }
}