package com.example.youtube6month.core.network


import com.example.youtube6month.BuildConfig
import com.example.youtube6month.core.base.BaseDataSource
import com.example.youtube6month.data.remote.YouTubeApiService
import com.example.youtube6month.utils.Constants

class RemoteDataSource(private val youTubeApiService: YouTubeApiService) : BaseDataSource() {

    suspend fun getPlaylists(nextPageToken: String) = getResult {
        youTubeApiService.getPlaylists(
            key = BuildConfig.API_KEY,
            pageToken = nextPageToken,
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            maxResults = 10,
        )
    }

    suspend fun getDetails(playlistId: String,nextPageToken: String) = getResult {
        youTubeApiService.getDetails(
            key = BuildConfig.API_KEY,
            pageToken = nextPageToken,
            part = Constants.PART,
            playlistId = playlistId,
            maxResults = 10,
        )
    }
}