package com.example.youtube6month.core.di

import com.example.youtube6month.core.network.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(get()) }
}