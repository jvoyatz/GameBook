package gr.jvoyatz.gamebook.libraries.network

import android.content.Context
import gr.jvoyatz.gamebook.libraries.network.dto.games.GameDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.updated.UpdatedGamesItemDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.HeadlineDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated.UpdatedHeadlineHeadlineItemDto
import retrofit2.http.GET

/**
 * Defines the contract used to communicate with the GameBook Web Service
 */
interface GameBookApi {
    @GET("5d7113513300000b2177973a")
    suspend fun fetchGames(): List<GameDto>

    @GET("5d7113ef3300000e00779746")
    suspend fun fetchHeadlines(): List<HeadlineDto>

    @GET("5d7114b2330000112177974d")
    suspend fun fetchUpdatedGames(): List<UpdatedGamesItemDto>

    @GET("5d711461330000d135779748")
    suspend fun fetchUpdatedHeadlines(): List<UpdatedHeadlineHeadlineItemDto>

    companion object : ApiFactory<GameBookApi> {
        override fun create(context: Context): GameBookApi = ApiProvider.getApi(context)
    }
}

internal interface ApiFactory<T> {
    fun create(context: Context): T
}