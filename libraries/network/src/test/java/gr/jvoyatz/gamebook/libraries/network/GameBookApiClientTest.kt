
package gr.jvoyatz.gamebook.libraries.network

import com.google.common.truth.Truth
import com.squareup.moshi.JsonDataException
import gr.jvoyatz.gamebook.libraries.network.dto.games.GamesDto
import gr.jvoyatz.gamebook.libraries.testing.ApiServer
import gr.jvoyatz.gamebook.libraries.testing.utils.TestUtils
import gr.jvoyatz.gamebook.libraries.testing.utils.TestUtils.loadResourceFile
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

/**
 * PoC of testing for GameBookClient -- tested fetchGames only
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GameBookApiClientTest: ApiServer<GameBookApi>() {

    override fun init() = GameBookApi::class.java

    @Test
    fun `fetch games - 200 OK `() = runTest {
        //given
        enqueue {
            success(rawGames!!)
        }

        //when
        val response = apiClient.fetchGames()

        //then
        Truth.assertThat(response).isNotNull()
        Truth.assertThat(response).isNotEmpty()
    }

    @Test
    fun `fetch games - 400 httpError `() = runTest {
        //given
        enqueue {
            httpError()
        }

        //then
        TestUtils.assertThrows<HttpException> {
            //when
            apiClient.fetchGames()
        }
    }


    @Test
    fun `fetch games - 500 ioException `() = runTest {
        //given
        enqueue {
            networkError()
        }

        //then
        TestUtils.assertThrows<IOException> {
            //when
            apiClient.fetchGames()
        }
    }

    @Test
    fun `fetch games - unknown (deserialization) error `() = runTest {
        //given
        enqueue {
            unknownError()
        }

        //then
        TestUtils.assertThrows<JsonDataException> {
            //when
            apiClient.fetchGames()
        }
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