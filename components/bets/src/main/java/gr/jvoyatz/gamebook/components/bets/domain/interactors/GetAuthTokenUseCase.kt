package gr.jvoyatz.gamebook.components.bets.domain.interactors

import gr.jvoyatz.gamebook.libraries.shared.resultdata.ResultData

fun interface GetAuthTokenUseCase: suspend () -> ResultData<Unit>