package gr.jvoyatz.gamebook.di

import android.os.Build
import android.os.Handler
import android.os.Looper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideAppContext(@ApplicationContext context: ApplicationContext) = context

    @Provides
    fun provideHandler(): Handler = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        Handler.createAsync(Looper.getMainLooper())
    } else {
        Handler(Looper.getMainLooper())
    }
}