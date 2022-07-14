package com.example.possumuschallenge.di

import com.example.possumuschallenge.data.PossumusRepository
import com.example.possumuschallenge.data.PossumusRepositoryImp
import com.example.possumuschallenge.data.PossumusService
import com.example.possumuschallenge.data.PossumusServiceImp
import com.example.possumuschallenge.ui.SharedViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val modules = module {

    single { provideKtorClient() }

    single<PossumusService> { PossumusServiceImp(get()) }

    single<PossumusRepository> { PossumusRepositoryImp(get()) }

    single { SharedViewModel(get()) }
}

fun provideKtorClient(): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
        install(HttpTimeout)
    }
}