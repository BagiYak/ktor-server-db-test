package com.example.di

import com.example.service.feature_user.repository.LoginService
import com.example.service.feature_user.repository.LoginServiceImpl
import com.example.service.feature_user.usecase.LoginUseCase
import com.example.service.feature_user.usecase.UserUseCases
import org.koin.dsl.module

val loginModule = module(createdAtStart = true) {

    single<LoginService> { LoginServiceImpl() }
    single {
        UserUseCases(
            login = LoginUseCase(get()) // get() Will resolve LoginService
        )
    }

}