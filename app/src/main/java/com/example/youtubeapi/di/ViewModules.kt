package com.example.youtubeapi.di

import com.example.youtubeapi.ui.playlistdetails.PlayListDetailsViewModel
import com.example.youtubeapi.ui.playlist.PlayListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val viewModules: Module = module {
    viewModel { PlayListViewModel(get()) }
    viewModel { PlayListDetailsViewModel(get()) }
}