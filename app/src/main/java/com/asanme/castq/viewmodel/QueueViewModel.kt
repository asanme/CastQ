package com.asanme.castq.viewmodel

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.asanme.castq.model.VideoUrl

class QueueViewModel(
    private val navController: NavHostController,
) : ViewModel() {
    var _videoQueue = mutableStateListOf<VideoUrl>()

}