package gr.jvoyatz.gamebook.libraries.network.dto.token


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthTokenRequest(
    @Json(name = "password")
    val password: String,
    @Json(name = "userName")
    val userName: String
)