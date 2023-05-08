package gr.jvoyatz.gamebook.libraries.network.config

import gr.jvoyatz.gamebook.libraries.network.TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


const val AUTHORIZATION = "Authorization"
const val BEARER = "Bearer"

internal class AuthBearerTokenInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if(request.url.toString().contains("5d8e4bd9310000a2612b5448"))
            return chain.proceed(request)

        return with(request) {
            this.newBuilder()
                .header(AUTHORIZATION, "$BEARER $TOKEN")
        }.build().run {
            chain.proceed(this)
        }
    }
}