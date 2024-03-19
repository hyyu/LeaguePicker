package fr.arrows.leaguepicker.data.leagues.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.arrows.leaguepicker.data.leagues.repository.FetchLeaguesRepositoryImpl
import fr.arrows.leaguepicker.domain.leagues.repository.FetchLeaguesRepository
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
