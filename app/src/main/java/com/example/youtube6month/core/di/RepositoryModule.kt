package com.example.youtube6month.core.di

import com.example.youtube6month.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}