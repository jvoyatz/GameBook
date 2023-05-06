package gr.jvoyatz.gamebook.libraries.shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface AppDispatchers{
    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    val main: CoroutineDispatcher
        get() = Dispatchers.Main

    val default: CoroutineDispatcher
        get() = Dispatchers.Default

    val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}