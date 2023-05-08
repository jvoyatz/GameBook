package gr.jvoyatz.gamebook.components.bets.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.jvoyatz.gamebook.components.bets.AppDispatchers
import gr.jvoyatz.gamebook.components.bets.AppDispatchersImpl
import gr.jvoyatz.gamebook.components.bets.data.BetsRepositoryImpl
import gr.jvoyatz.gamebook.components.bets.domain.interactors.GetAuthTokenUseCase
import gr.jvoyatz.gamebook.components.bets.domain.interactors.GetEventDataUseCase
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
        GetEventDataUseCase { isInit ->
            if (!isInit) repository.getUpdatedData()
            else repository.getData()
        }

    @Provides
    fun provideGetAuthTokenUseCase(repository: BetsRepository) =
        GetAuthTokenUseCase {
            repository.getToken()
        }

    @InstallIn(SingletonComponent::class)
    @Module
    interface Bindings {
        @Binds
        fun bindAppDispatchers(impl: AppDispatchersImpl): AppDispatchers
    }
}