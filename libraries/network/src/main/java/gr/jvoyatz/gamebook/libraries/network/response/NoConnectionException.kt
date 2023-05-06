package gr.jvoyatz.gamebook.libraries.network.response

import java.io.IOException


class NoConnectionException(private val customMessage: String? = null) : IOException() {

    override val message: String
        get() = customMessage ?: "No Internet Connection"
}

@Suppress("unused")
val Throwable.isNoConnectionException get() = this is NoConnectionException