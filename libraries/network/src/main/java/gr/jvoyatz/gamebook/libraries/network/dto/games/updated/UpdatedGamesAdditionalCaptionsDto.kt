package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesAdditionalCaptionsDto(
    @Json(name = "competitor1")
    val competitor1: String,
    @Json(name = "competitor1ImageId")
    val competitor1ImageId: Int,
    @Json(name = "competitor2")
    val competitor2: String,
    @Json(name = "competitor2ImageId")
    val competitor2ImageId: Int,
    @Json(name = "type")
    val type: Int
)