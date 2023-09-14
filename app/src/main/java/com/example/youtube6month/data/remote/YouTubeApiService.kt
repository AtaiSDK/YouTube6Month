package com.example.youtube6month.data.remote

import com.example.youtube6month.data.model.PlaylistsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("key") key: String,
        @Query("pageToken") pageToken: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlaylistsModel>

    @GET("playlistItems")
    suspend fun getDetails(
        @Query("key") key: String,
        @Query("pageToken") pageToken: String,
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int,
    ): Response<PlaylistsModel>
}