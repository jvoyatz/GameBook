package gr.jvoyatz.gamebook.components.bets.data.sources

import gr.jvoyatz.gamebook.libraries.network.GameBookApi
import gr.jvoyatz.gamebook.libraries.network.dto.games.GamesDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.HeadlinesDto
import gr.jvoyatz.gamebook.libraries.network.response.ApiResponse
import gr.jvoyatz.gamebook.libraries.network.response.safeRawApiCall


/**
 * Internal datasource class which is responsible of executing the network request.
 * Then after processing on the result, it returns the state of the request's response body.
 * That means, it can be successful, error (httpError, etc)
 * @see [ApiResponse]
 */
internal class BetsRemoteDataSource(
    private val gameBookApi: GameBookApi
) {
    suspend fun getGames(): ApiResponse<List<GamesDto>, String> = safeRawApiCall {
        gameBookApi.fetchGames()
    }

    suspend fun getUpdatedGames(): ApiResponse<List<GamesDto>, String> = safeRawApiCall {
        gameBookApi.fetchUpdatedGames()
    }

    suspend fun getHeadlines(): ApiResponse<List<HeadlinesDto>, String> = safeRawApiCall {
        gameBookApi.fetchHeadlines()
    }
    suspend fun getUpdatedHeadlines(): ApiResponse<List<HeadlinesDto>, String> = safeRawApiCall {
        gameBookApi.fetchUpdatedHeadlines()
    }
}