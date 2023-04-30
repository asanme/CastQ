@file:OptIn(ExperimentalMaterial3Api::class)

package com.asanme.castq.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.asanme.castq.R
import com.asanme.castq.data.helper.VideoHelper
import com.asanme.castq.data.model.Video
import com.asanme.castq.view.ui.components.VideoItem
import com.asanme.castq.viewmodel.QueueViewModel

@Composable
fun QueueView(
    navController: NavHostController
) {
    val context = LocalContext.current
    val videoDatabase = VideoHelper.getVideoDatabase(context)
    val queueViewModel = QueueViewModel(navController, videoDatabase)

    var urlText by rememberSaveable {
        mutableStateOf("remember the text")
    }

    Scaffold(
        topBar = {
            QueueTopBar(queueViewModel)
        },
        content = { innerPadding ->
            QueueViewBody(innerPadding, queueViewModel)
        }
    )
}

@Composable
private fun QueueTopBar(
    queueViewModel: QueueViewModel
) {
    TopAppBar(
        title = {
            Text(
                text = "Queue",
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.add_video),
                contentDescription = stringResource(
                    id = R.string.add_description
                ),
                modifier = Modifier.clickable {
                    queueViewModel.addVideoToQueue(Video(url = "testing-urls-room"))
                }
            )
        }
    )
}

@Composable
private fun QueueViewBody(
    innerPadding: PaddingValues,
    queueViewModel: QueueViewModel
) {
    val videos = queueViewModel.videoQueue

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (videos.isEmpty()) {
            item {
                Icon(
                    painter = painterResource(id = R.drawable.video_queue),
                    contentDescription = stringResource(id = R.string.empty_queue_description),
                    modifier = Modifier.size(75.dp)
                )

                Box(modifier = Modifier.size(10.dp))

                Text(
                    stringResource(id = R.string.empty_queue)
                )
            }
        } else {
            items(count = videos.size) { index ->
                VideoItem(Video(url = "${videos[index]}"))
            }
        }
    }
}
