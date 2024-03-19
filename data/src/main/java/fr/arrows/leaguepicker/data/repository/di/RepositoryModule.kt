package fr.arrows.leaguepicker.data.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.arrows.leaguepicker.data.repository.leagues.FetchLeaguesRepositoryImpl
import fr.arrows.leaguepicker.domain.repository.leagues.FetchLeaguesRepository
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFetchLeaguesRepositoryImplementation(
        httpClient: HttpClient
    ): FetchLeaguesRepositoryImpl = FetchLeaguesRepositoryImpl(httpClient)

    @Provides
    fun provideFetchLeaguesRepository(
        impl: FetchLeaguesRepositoryImpl
    ): FetchLeaguesRepository = impl

}
