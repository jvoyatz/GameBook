package gr.jvoyatz.gamebook.libraries.network.config

import android.content.Context
import gr.jvoyatz.gamebook.libraries.network.response.NoConnectionException
import gr.jvoyatz.gamebook.libraries.utils.android.isOnline
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


internal class ConnectivityInterceptor(private val context: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!context.isOnline) {
            throw NoConnectionException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}
