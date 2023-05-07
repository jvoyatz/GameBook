package gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedHeadlineBetItemDto(
    @Json(name = "caption")
    val caption: String,
    @Json(name = "code")
    val code: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "instanceCaption")
    val instanceCaption: String? = null,
    @Json(name = "isAvailable")
    val isAvailable: Boolean,
    @Json(name = "oddsText")
    val oddsText: String,
    @Json(name = "price")
    val price: Double
)