package gr.jvoyatz.gamebook.libraries.network.config

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

internal class CustomLoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val request = originalRequest.newBuilder().url(originalRequest.url).build()

        return chain.proceed(request).also {
            Timber.w("RESPONSE FOR REQUEST [$request] IS [${it.isSuccessful}]")
        }
    }
}