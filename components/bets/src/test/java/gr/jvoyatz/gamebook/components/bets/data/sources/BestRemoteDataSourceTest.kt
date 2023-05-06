package gr.jvoyatz.gamebook.components.bets.data.sources

import com.google.common.truth.Truth
import gr.jvoyatz.gamebook.libraries.network.GameBookApi
import gr.jvoyatz.gamebook.libraries.network.dto.games.GamesDto
import gr.jvoyatz.gamebook.libraries.network.response.ApiResponse
import gr.jvoyatz.gamebook.libraries.network.response.asHttpError
import gr.jvoyatz.gamebook.libraries.network.response.asSuccess
import gr.jvoyatz.gamebook.libraries.network.response.isError
import gr.jvoyatz.gamebook.libraries.network.response.isSuccess
import gr.jvoyatz.gamebook.libraries.testing.ApiServer
import gr.jvoyatz.gamebook.libraries.testing.utils.TestUtils
import gr.jvoyatz.gamebook.libraries.testing.utils.TestUtils.loadResourceFile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.BeforeClass
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BestRemoteDataSourceTest : ApiServer<GameBookApi>(){
    
    private lateinit var sut : BetsRemoteDataSource
    override fun init(): Class<GameBookApi> = GameBookApi::class.java

    override fun setupServer() {
        super.setupServer()
        sut = BetsRemoteDataSource(apiClient)
    }

    @Test
    fun `get games - 200 OK `() = runTest {
        //given
        enqueue {
            success(rawGames!!)
        }

        //when
        val response = sut.getGames()


        //then
        Truth.assertThat(response).isNotNull()
        Truth.assertThat(response.isSuccess()).isTrue()
        Truth.assertThat(response.asSuccess()!!.body).isNotEmpty()
    }

    @Test
    fun `get games - 400 httpError `() = runTest {
        //given
        enqueue {
            httpError()
        }

        //when
        val apiResponse = sut.getGames()

        Truth.assertThat(apiResponse).isNotNull()
        Truth.assertThat(apiResponse.isError()).isTrue()
        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.HttpError::class.java)
        val httperror = apiResponse.asHttpError()
        Truth.assertThat(httperror!!.code).isEqualTo(400)
    }


    @Test
    fun `get games - 500 ioException `() = runTest {
        //given
        enqueue {
            networkError()
        }

        //when
        val apiResponse = sut.getGames()

        //then
        Truth.assertThat(apiResponse).isNotNull()
        Truth.assertThat(apiResponse.isError()).isTrue()
        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.NetworkError::class.java)
    }

    @Test
    fun `get games - unknown (deserialization) error `() = runTest {
        //given
        enqueue {
            unknownError()
        }

        //when
        val apiResponse = sut.getGames()

        //then
        Truth.assertThat(apiResponse).isNotNull()
        Truth.assertThat(apiResponse.isError()).isTrue()
        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.UnexpectedError::class.java)
    }

    companion object {
        var rawGames: String? = null
        var gamesDto: List<GamesDto>? = null
            get() = field!!

        @JvmStatic
        @BeforeClass
        fun loadData() {
            rawGames = loadResourceFile("/games.json")
            gamesDto = TestUtils.getData<GamesDto, GamesDto>("/games.json") { this }
        }
    }
}