package gr.jvoyatz.gamebook.components.bets.data.mappers

import com.google.common.truth.Truth
import gr.jvoyatz.gamebook.components.bets.data.mappers.GamesMapper.toHeadlines
import gr.jvoyatz.gamebook.libraries.network.dto.games.GameDto
import gr.jvoyatz.gamebook.libraries.testing.utils.TestUtils
import gr.jvoyatz.gamebook.libraries.testing.utils.TestUtils.loadResourceFile
import org.junit.BeforeClass
import org.junit.Test

class MappersTest{

    @Test
    fun `test that games dtos to games event happens succesfully`(){
        //given
        val dtos = gamesDto!!

        //when
        val models = dtos.toHeadlines()

        //then
        Truth.assertThat(models.size).isEqualTo(dtos.size)
    }

    companion object {
        var rawGames: String? = null
        var gamesDto: List<GameDto>? = null
            get() = field!!

        @JvmStatic
        @BeforeClass
        fun loadData() {
            rawGames = loadResourceFile("/games.json")
            gamesDto = TestUtils.getData<GameDto, GameDto>("/games.json") { this }
        }
    }
}