package gr.jvoyatz.gamebook.libraries.utils.android

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface Bindings {
    @Binds
    fun bindAppDispatchers(impl: AppDispatchersImpl): AppDispatchers
}