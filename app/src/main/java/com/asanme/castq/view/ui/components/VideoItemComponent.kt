package com.asanme.castq.view.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.ResistanceConfig
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.asanme.castq.R
import com.asanme.castq.data.model.Video
import com.asanme.castq.viewmodel.QueueViewModel
import kotlin.math.roundToInt

@Composable
fun VideoItemComponent(
    video: Video,
    queueViewModel: QueueViewModel
) {
    SwipeableSample()
    /*
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        VideoItemBody(video)
    }*/
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeableSample() {
    val width = 200.dp
    val squareSize = 50.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                orientation = Orientation.Horizontal,
            )
            .background(Color.LightGray)
    ) {
        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .background(Color.DarkGray)
        )
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