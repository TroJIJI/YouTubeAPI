package com.example.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.model.PlayList
import com.example.youtubeapi.repository.Repository
import retrofit2.Response


class PlayListViewModel(private val repository: Repository) : ViewModel() {

    fun getPlayList(): LiveData<Response<PlayList>> {
        return repository.createCall()
    }
}