package com.example.youtubeapi.ui.playlistdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtubeapi.model.PlayList
import com.example.youtubeapi.repository.Repository
import retrofit2.Response

class PlayListDetailsViewModel(private val repository: Repository): ViewModel() {

    fun getPlaylistDetails(id : String):LiveData<Response<PlayList>>{
        return repository.createDetails(id)
    }
}