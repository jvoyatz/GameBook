package gr.jvoyatz.gamebook.components.bets.domain.interactors


/**
 * Calls the methods which return the needed data from [BetsRepository]
 */
fun interface GetBetsUseCase: suspend (Boolean) -> Unit
