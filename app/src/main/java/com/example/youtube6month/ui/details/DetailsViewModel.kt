package com.example.youtube6month.ui.details

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.youtube6month.core.base.BaseViewModel
import com.example.youtube6month.data.model.PlaylistsModel
import com.example.youtube6month.data.repository.Repository

class DetailsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getDetails(playlistId: String): LiveData<PagingData<PlaylistsModel.Item>> =
        repository.getDetails(playlistId)
}