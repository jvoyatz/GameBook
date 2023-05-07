package gr.jvoyatz.gamebook.components.bets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.jvoyatz.gamebook.components.bets.data.BetsRepositoryImpl
import gr.jvoyatz.gamebook.components.bets.domain.interactors.getEventDataUseCase
import gr.jvoyatz.gamebook.components.bets.domain.repositories.BetsRepository
import gr.jvoyatz.gamebook.libraries.network.GameBookApi


@Module
@InstallIn(SingletonComponent::class)
object ComponentModule {

    @Provides
    fun provideBetsRepository(gameBookApi: GameBookApi): BetsRepository {
        return BetsRepositoryImpl.create(gameBookApi)
    }

    @Provides
    fun provideGetBetsUseCase(repository: BetsRepository) =
        getEventDataUseCase { isInit ->
            if (!isInit) repository.getUpdatedData()
            else repository.getData()
        }
}