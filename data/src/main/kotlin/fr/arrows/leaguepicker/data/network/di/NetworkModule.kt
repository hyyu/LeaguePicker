package fr.arrows.leaguepicker.data.network.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.arrows.leaguepicker.data.network.values.Endpoints
import fr.arrows.leaguepicker.data.network.values.NetworkConstants
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideHttpClient(
        json: Json
    ): HttpClient = HttpClient(Android) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            requestTimeoutMillis = NetworkConstants.REQUEST_TIMEOUT_MILLIS
            connectTimeoutMillis = NetworkConstants.CONNECT_TIMEOUT_MILLIS
            socketTimeoutMillis = NetworkConstants.SOCKET_TIMEOUT_MILLIS
        }
        install(ContentNegotiation) {
            json(json)
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = Endpoints.HOST
            }
            contentType(ContentType.Application.Json)
        }
    }.also { httpClient ->
        httpClient.plugin(HttpSend).intercept { request ->
            execute(request).let { originalCall ->
                when (originalCall.response.status) {
                    HttpStatusCode.Unauthorized -> Log.e("HttpClient/Unauthorized", "Request was unauthorized")
                }
                return@intercept originalCall
            }
        }
    }

}
