package com.example.di

import com.example.config.AppConfig
import com.example.database.DatabaseFactory
import com.example.database.DatabaseFactoryImpl
import com.example.service.feature_user.repository.LoginService
import com.example.service.feature_user.repository.LoginServiceImpl
import com.example.service.feature_user.usecase.LoginUseCase
import com.example.service.feature_user.usecase.UserUseCases
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

@KoinReflectAPI
val appModule = module {

    single { AppConfig() }

    single<LoginService> { LoginServiceImpl() }

    single {
        UserUseCases(
            login = LoginUseCase(get()) // get() Will resolve LoginService
        )
    }

    single<DatabaseFactory> {
        DatabaseFactoryImpl(
            appConfig = get() // get() Will resolve AppConfig
        )
    }

}