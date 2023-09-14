package com.example.youtube6month.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.youtube6month.core.network.RemoteDataSource
import com.example.youtube6month.data.model.PlaylistsModel
import com.example.youtube6month.data.paging.DetailsPagingSource
import com.example.youtube6month.data.paging.PlaylistsPagingSource

class Repository(private val remoteDataSource: RemoteDataSource) {

    fun getPlaylist(): LiveData<PagingData<PlaylistsModel.Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10,),
            pagingSourceFactory = { PlaylistsPagingSource(remoteDataSource = remoteDataSource) }
        ).liveData
    }

    fun getDetails(playlistId: String): LiveData<PagingData<PlaylistsModel.Item>> {
        return Pager(
            config = PagingConfig(pageSize = 10,),
            pagingSourceFactory = { DetailsPagingSource(
                remoteDataSource = remoteDataSource,
                playlistId = playlistId) }
        ).liveData
    }
}