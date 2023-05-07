package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesItemDto(
    @Json(name = "betViews")
    val betViews: List<UpdatedGamesBetViewDto>,
    @Json(name = "caption")
    val caption: String,
    @Json(name = "hasHighlights")
    val hasHighlights: Boolean,
    @Json(name = "marketViewKey")
    val marketViewKey: String,
    @Json(name = "marketViewType")
    val marketViewType: String,
    @Json(name = "modelType")
    val modelType: String,
    @Json(name = "totalCount")
    val totalCount: Int
)