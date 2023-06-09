package gr.jvoyatz.gamebook.libraries.network.dto.token


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthTokenResponse(
    @Json(name = "access_token")
    val accessToken: String,
    @Json(name = "token_type")
    val tokenType: String
)