package com.example.youtubeapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.`object`.Constant
import com.example.youtubeapi.model.PlayList
import com.example.youtubeapi.remote.YouTubeApi
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class Repository(private val youtubeApi: YouTubeApi) {

    fun createCall(): LiveData<Response<PlayList>> = liveData(Dispatchers.IO) {
        val response = youtubeApi.getPlayLists(
            Constant.PART, Constant.CHANNEL_ID,
            BuildConfig.API_KEY,
            Constant.MAX_RESULT
        )
        emit(response)
    }

    fun createDetails(id: String): LiveData<Response<PlayList>> = liveData(Dispatchers.IO) {
        val response = youtubeApi.getPlayListsDetails(
            Constant.PART,
            id,
            BuildConfig.API_KEY,
            Constant.MAX_RESULT
        )
        emit(response)
    }
}