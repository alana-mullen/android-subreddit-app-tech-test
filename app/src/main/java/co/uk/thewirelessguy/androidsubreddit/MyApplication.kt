package co.uk.thewirelessguy.androidsubreddit

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        initLogging()
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            AndroidRemoteDebugger.init(applicationContext)
            Timber.plant(Timber.DebugTree())
        }
    }
}