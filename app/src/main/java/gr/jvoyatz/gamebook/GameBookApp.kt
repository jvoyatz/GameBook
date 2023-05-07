package gr.jvoyatz.gamebook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import gr.jvoyatz.gamebook.utils.TimberDebugTree
import timber.log.Timber

@HiltAndroidApp
class GameBookApp: Application() {
    override fun onCreate() {
        super.onCreate()

      //  if(BuildConfig.DEBUG){
            Timber.plant(TimberDebugTree())
       // }
    }
}