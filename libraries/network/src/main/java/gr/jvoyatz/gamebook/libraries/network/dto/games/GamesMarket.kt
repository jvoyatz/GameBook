package gr.jvoyatz.gamebook.libraries.network.dto.games


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesMarket(
    @Json(name = "betItems")
    val betItems: List<GamesBetItemDto>,
    @Json(name = "betTypeSysname")
    val betTypeSysname: String,
    @Json(name = "marketId")
    val marketId: Int
)