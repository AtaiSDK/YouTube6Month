package com.example.youtube6month.ui.playlists

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.youtube6month.core.base.BaseViewModel
import com.example.youtube6month.data.model.PlaylistsModel
import com.example.youtube6month.data.repository.Repository

class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlayList(): LiveData<PagingData<PlaylistsModel.Item>> = repository.getPlaylist()
}