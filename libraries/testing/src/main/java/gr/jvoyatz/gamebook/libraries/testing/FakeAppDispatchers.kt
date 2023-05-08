package gr.jvoyatz.gamebook.libraries.testing

import gr.jvoyatz.gamebook.libraries.utils.android.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

/**
 * Replaces the default values with the test dispatcher
 */
class FakeAppDispatchers(
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()) :
    AppDispatchers {

    override val io: CoroutineDispatcher
        get() = dispatcher
    override val main: CoroutineDispatcher
        get() = dispatcher
    override val default: CoroutineDispatcher
        get() = dispatcher
    override val unconfined: CoroutineDispatcher
        get() = dispatcher
}