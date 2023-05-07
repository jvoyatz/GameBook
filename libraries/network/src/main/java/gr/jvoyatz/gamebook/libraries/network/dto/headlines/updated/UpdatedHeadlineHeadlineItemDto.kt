package gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedHeadlineHeadlineItemDto(
    @Json(name = "betViews")
    val betViews: List<UpdatedHeadlineBetViewDto>,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "marketViewKey")
    val marketViewKey: String,
    @Json(name = "marketViewType")
    val marketViewType: String,
    @Json(name = "modelType")
    val modelType: String
)