package gr.jvoyatz.gamebook.components.bets.data

import gr.jvoyatz.gamebook.components.bets.data.sources.BetsRemoteDataSource
import gr.jvoyatz.gamebook.components.bets.domain.repositories.BetsRepository
import gr.jvoyatz.gamebook.libraries.network.GameBookApi

/**
 * Implementation for [BetsRepository]
 *
 * Contains a create method to create a new instance
 */
internal class BetsRepositoryImpl
    internal constructor(datasource: BetsRemoteDataSource) : BetsRepository {

    override fun getBets() {
        TODO("Not yet implemented")
    }

    override fun getUpdatedBets() {
        TODO("Not yet implemented")
    }


    internal companion object {
        fun create(gameBookApi: GameBookApi): BetsRepository {
            val datasource = BetsRemoteDataSource(gameBookApi)
            return BetsRepositoryImpl(datasource)
        }
    }
}