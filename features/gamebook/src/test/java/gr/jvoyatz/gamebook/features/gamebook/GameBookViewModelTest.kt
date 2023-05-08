package gr.jvoyatz.gamebook.features.gamebook

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth
import gr.jvoyatz.gamebook.components.bets.domain.interactors.GetAuthTokenUseCase
import gr.jvoyatz.gamebook.components.bets.domain.interactors.GetEventDataUseCase
import gr.jvoyatz.gamebook.components.bets.domain.models.EventsDataContainer
import gr.jvoyatz.gamebook.libraries.shared.resultdata.ResultData
import gr.jvoyatz.gamebook.libraries.testing.FakeAppDispatchers
import gr.jvoyatz.gamebook.libraries.testing.MainDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GameBookViewModelTest{
    @get:Rule
    val rule = MainDispatcherRule()

    @SpyK
    private var savedStateHandle = SavedStateHandle()

    private val appDispatchers = FakeAppDispatchers()

    private val getData: GetEventDataUseCase = mockk()
    private val getAuthTokenUseCase: GetAuthTokenUseCase = mockk()

    private lateinit var sut: GameBookViewModel


    @Before
    fun setup(){
        MockKAnnotations.init(this)
        sut = GameBookViewModel(
            savedStateHandle,
            getData, getAuthTokenUseCase, appDispatchers)
    }

    @Test
    fun `on authenticate event, then get token `() = runTest {
        //given
        val event = GameBookContract.Event.Authenticate

        //when
        sut.onNewEvent(event)

        sut
        //then
        coVerify(exactly = 1) { getAuthTokenUseCase() }
    }

    @Test
    fun `on initialize event, then get data `() = runTest {
        //given
        val event = GameBookContract.Event.Initialize

        //when
        sut.onNewEvent(event)

        sut
        //then
        coVerify(exactly = 1) { getData(true) }
    }

    @Test
    fun `on empty response getData event, then state is no data `() = runTest {
        //given
        val event = GameBookContract.Event.Initialize
        coEvery { getData(any()) } returns  ResultData.success(EventsDataContainer(listOf(), listOf()))

        //when
        sut.uiState.test {
            sut.onNewEvent(event)
            skipItems(1)
            val state = awaitItem().state


            Truth.assertThat(state).isEqualTo(GameBookContract.GameBookUiState.NoData)
        }
    }
}