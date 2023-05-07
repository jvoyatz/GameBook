package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesMarketCaptionDto(
    @Json(name = "betCaptions")
    val betCaptions: Any?=null,
    @Json(name = "betTypeSysname")
    val betTypeSysname: String,
    @Json(name = "marketCaption")
    val marketCaption: String
)