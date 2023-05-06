package gr.jvoyatz.gamebook.libraries.network.config

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


const val AUTHORIZATION = "Authorization"
const val BEARER = "Bearer"

internal class AuthBearerTokenInterceptor(private val token: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain.request()) {
            this.newBuilder()
                .header(AUTHORIZATION, "$BEARER $token")
        }.build().run {
            chain.proceed(this)
        }
    }
}