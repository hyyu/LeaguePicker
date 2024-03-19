package fr.arrows.leaguepicker.data.teams.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.arrows.leaguepicker.data.teams.repository.FetchTeamsRepositoryImpl
import fr.arrows.leaguepicker.domain.teams.repository.FetchTeamsRepository
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TeamsModule {

    @Provides
    @Singleton
    fun provideFetchTeamsRepositoryImplementation(
        httpClient: HttpClient
    ): FetchTeamsRepositoryImpl = FetchTeamsRepositoryImpl(httpClient)

    @Provides
    fun provideFetchTeamsRepository(
        impl: FetchTeamsRepositoryImpl
    ): FetchTeamsRepository = impl

}
