package gr.jvoyatz.gamebook.libraries.network.dto.games.updated


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdatedGamesMarketDto(
    @Json(name = "betItems")
    val betItems: List<UpdatedGamesBetItemDto>,
    @Json(name = "betTypeSysname")
    val betTypeSysname: String,
    @Json(name = "marketId")
    val marketId: Int
)