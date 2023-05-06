package gr.jvoyatz.gamebook.components.bets.domain


/**
 * Calls the methods which return the needed data from [BetsRepository]
 */
fun interface GetBetsUseCase: (Boolean) -> Unit
