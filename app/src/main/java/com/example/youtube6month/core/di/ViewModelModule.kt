package com.example.youtube6month.core.di

import com.example.youtube6month.ui.details.DetailsViewModel
import com.example.youtube6month.ui.player.PlayerViewModel
import com.example.youtube6month.ui.playlists.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { PlayerViewModel() }
}