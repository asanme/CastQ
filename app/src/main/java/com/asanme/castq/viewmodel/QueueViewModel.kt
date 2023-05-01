package com.asanme.castq.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.asanme.castq.data.database.VideoDatabase
import com.asanme.castq.data.model.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
class QueueViewModel(
    private val navController: NavHostController,
    videoDatabase: VideoDatabase,
) : ViewModel() {
    private val _videoDao = videoDatabase.videoDao()
    private var _videoQueue = mutableStateListOf<Video>()
    private var _modalState = MutableStateFlow(
        ModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden
        )
    )

    var modalState = _modalState.asStateFlow()
    var videoQueue: List<Video> = _videoQueue

    init {
        getVideos()
    }

    private fun closeModal() {
        viewModelScope.launch {
            _modalState.emit(ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden))
        }
    }

    fun openModal() {
        viewModelScope.launch {
            _modalState.emit(ModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded))
        }
    }

    fun addVideo(newVideo: Video) {
        viewModelScope.launch {
            _videoDao.insertVideo(newVideo)
            closeModal()
        }
    }

    fun updateVideo(selectedVideo: Video) {
        viewModelScope.launch {
            _videoDao.deleteVideo(selectedVideo)
        }
    }

    fun deleteVideo(selectedVideo: Video) {
        viewModelScope.launch {
            _videoDao.deleteVideo(selectedVideo)
        }
    }

    private fun getVideos() {
        viewModelScope.launch {
            _videoDao.getVideos().collect { videoQueue ->
                _videoQueue.clear()
                _videoQueue.addAll(videoQueue)
            }
        }
    }
}