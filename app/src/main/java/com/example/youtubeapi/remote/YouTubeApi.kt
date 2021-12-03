package com.example.youtubeapi.remote

import com.example.youtubeapi.model.PlayList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {

    @GET("playlists")
    suspend fun getPlayLists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResult: Int
    ): Response<PlayList>

    @GET("playlistItems")
    suspend fun getPlayListsDetails(
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResult: Int
    ): Response<PlayList>
}