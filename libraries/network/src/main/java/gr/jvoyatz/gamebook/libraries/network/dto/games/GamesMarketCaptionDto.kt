package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesMarketCaptionDto(
    @Json(name = "betCaptions")
    val betCaptions: Any?,
    @Json(name = "betTypeSysname")
    val betTypeSysname: String,
    @Json(name = "marketCaption")
    val marketCaption: String
)