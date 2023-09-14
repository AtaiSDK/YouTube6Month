package com.example.youtube6month.core.di

import com.example.youtube6month.core.network.networkModule

val koinModules = listOf(
    repositoryModule,
    networkModule,
    remoteDataSourceModule,
    viewModelModule,
)