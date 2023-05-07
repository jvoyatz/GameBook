package gr.jvoyatz.gamebook.components.bets.data

import gr.jvoyatz.gamebook.components.bets.data.mappers.GamesMapper.toGames
import gr.jvoyatz.gamebook.components.bets.data.mappers.HeadlinesMapper.toHeadlines
import gr.jvoyatz.gamebook.components.bets.data.mappers.UpdatedGamesMapper.toGames
import gr.jvoyatz.gamebook.components.bets.data.mappers.UpdatedHeadlinesMapper.toHeadlines
import gr.jvoyatz.gamebook.components.bets.data.sources.BetsRemoteDataSource
import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.components.bets.domain.models.GameBookException
import gr.jvoyatz.gamebook.components.bets.domain.models.games.Game
import gr.jvoyatz.gamebook.components.bets.domain.models.headlines.Headline
import gr.jvoyatz.gamebook.components.bets.domain.repositories.BetsRepository
import gr.jvoyatz.gamebook.libraries.network.GameBookApi
import gr.jvoyatz.gamebook.libraries.network.dto.games.GameDto
import gr.jvoyatz.gamebook.libraries.network.dto.games.updated.UpdatedGamesItemDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.HeadlineDto
import gr.jvoyatz.gamebook.libraries.network.dto.headlines.updated.UpdatedHeadlineHeadlineItemDto
import gr.jvoyatz.gamebook.libraries.network.response.extractError
import gr.jvoyatz.gamebook.libraries.network.response.invoke
import gr.jvoyatz.gamebook.libraries.network.response.isSuccess
import gr.jvoyatz.gamebook.libraries.shared.resultdata.ResultData
import gr.jvoyatz.gamebook.libraries.shared.resultdata.suspendedResultOf
import timber.log.Timber

/**
 * Implementation for [BetsRepository]
 *
 * Contains a create method to create a new instance
 */
internal class BetsRepositoryImpl
internal constructor(
    private val datasource: BetsRemoteDataSource
) : BetsRepository {

    private lateinit var cachedData: EventsDataContainer

    override suspend fun getData(): ResultData<EventsDataContainer> {
        Timber.d("getData() called")
        return suspendedResultOf {
            val headlines = getInitHeadlines()
            val games = getGames()
            EventsDataContainer(headlines.toHeadlines(), games.toGames()).also {
                cachedData = it
            }
        }
    }

    override suspend fun getUpdatedData(): ResultData<EventsDataContainer> {
        Timber.d("getUpdatedData() called")
        return suspendedResultOf {
            val headlines = getUpdatedHeadlines()
            val games = getUpdatedGames()

            getMergedWithCacheEventData(headlines.toHeadlines(), games.toGames()).let {
                EventsDataContainer(it.first, it.second)
            }
        }
    }

    private suspend fun getInitHeadlines(): List<HeadlineDto> {
        Timber.d("getInitHeadlines() called")
        val response = datasource.getHeadlines()

        return if (response.isSuccess()) {
            response() ?: throw GameBookException("headlines are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    private suspend fun getUpdatedHeadlines(): List<UpdatedHeadlineHeadlineItemDto> {
        Timber.d("getUpdatedHeadlines() called")
        val response = datasource.getUpdatedHeadlines()
        return if (response.isSuccess()) {
            response() ?: throw GameBookException("headlines are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    private suspend fun getGames(): List<GameDto> {
        val response = datasource.getGames()

        return if (response.isSuccess()) {
            response() ?: throw GameBookException("games are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    private suspend fun getUpdatedGames(): List<UpdatedGamesItemDto> {
        val response = datasource.getUpdatedGames()

        return if (response.isSuccess()) {
            response() ?: throw GameBookException("games are null")
        } else {
            throw GameBookException(response.extractError() ?: "unknown error")
        }
    }

    private fun getMergedWithCacheEventData(headlines: List<Headline>, games: List<Game>): Pair<List<Headline>, List<Game>>{
        if(!::cachedData.isInitialized)
            return headlines to games

        val mergedHeadlines = (cachedData.headlines + headlines)
            .flatMap { it.betViews }
            .distinctBy { it.id }
            .toList()
            .run { listOf(Headline(this))}

        val duplGames = games.toMutableList()
        duplGames.addAll(cachedData.games)



//        val mergedGames = (cachedData.games + games + duplGames)
//            .flatMap { it.betViews }
//            .distinctBy { it.competitionCaption }
//            .toList()



        return mergedHeadlines to cachedData.games + duplGames
    }

    internal companion object {
        fun create(gameBookApi: GameBookApi): BetsRepository {
            val datasource = BetsRemoteDataSource(gameBookApi)
            return BetsRepositoryImpl(datasource)
        }
    }
}