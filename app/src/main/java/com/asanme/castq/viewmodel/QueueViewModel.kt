package com.asanme.castq.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.asanme.castq.data.database.VideoDatabase
import com.asanme.castq.data.model.Video
import kotlinx.coroutines.launch

class QueueViewModel(
    private val navController: NavHostController,
    private val videoDatabase: VideoDatabase,
) : ViewModel() {
    private var _videoQueue = mutableStateListOf<Video>()
    var videoQueue: List<Video> = _videoQueue

    init {
        getVideos()
    }

    fun getVideos() {
        viewModelScope.launch {
            _videoQueue.clear()
            _videoQueue.addAll(videoDatabase.videoDao().getAllVideos())
        }
    }
}