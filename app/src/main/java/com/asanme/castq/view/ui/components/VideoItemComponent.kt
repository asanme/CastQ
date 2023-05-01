package com.asanme.castq.view.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asanme.castq.R
import com.asanme.castq.data.model.Video
import com.asanme.castq.viewmodel.QueueViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideoItemComponent(
    video: Video,
    queueViewModel: QueueViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .combinedClickable(
                enabled = true,
                onLongClick = { queueViewModel.deleteVideo(video) }
            ) { }
    ) {
        VideoItemBody(video)
    }
}

@Composable
private fun VideoItemBody(video: Video) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        VideoThumbnail()
        VideoInformation(video)
    }
}

@Composable
private fun VideoInformation(video: Video) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "This is a sample",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start
        )

        Text(
            text = "Video URL: ${video.url}",
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Start,
            maxLines = 1
        )
    }
}

@Composable
private fun VideoThumbnail() {
    Column(
        modifier = Modifier.size(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TODO Add Image loading with Glide
        Icon(
            painter = painterResource(id = R.drawable.add_video),
            contentDescription = stringResource(id = R.string.add_description)
        )
    }
}