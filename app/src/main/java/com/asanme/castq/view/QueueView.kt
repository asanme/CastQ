@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.asanme.castq.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.asanme.castq.view.ui.components.TextFieldComponent
import com.asanme.castq.view.ui.components.VideoItemComponent
import com.asanme.castq.viewmodel.QueueViewModel

// TODO Replace all string with stringResource
@Composable
fun QueueView(
    navController: NavHostController
) {
    val context = LocalContext.current
    val videoDatabase = VideoHelper.getVideoDatabase(context)
    val queueViewModel = QueueViewModel(navController, videoDatabase)

    Scaffold(
        topBar = {
            QueueViewTopBar(queueViewModel)
        },
        content = { innerPadding ->
            QueueViewBottomModal(queueViewModel = queueViewModel) {
                Row(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    QueueViewBody(queueViewModel)
                }
            }
        },
    )
}

@Composable
private fun QueueViewTopBar(
    queueViewModel: QueueViewModel
) {
    TopAppBar(
        title = {
            Text(
                text = "Queue",
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = {
            IconButton(
                onClick = {
                    queueViewModel.openModal()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_video),
                    contentDescription = stringResource(id = R.string.add_description)
                )
            }
        }
    )
}

@Composable
@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterialApi::class)
private fun QueueViewBottomModal(
    queueViewModel: QueueViewModel,
    content: @Composable () -> Unit
) {
    val modalState = queueViewModel.modalState.collectAsState()

    return ModalBottomSheetLayout(
        sheetState = modalState.value,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetElevation = 10.dp,
        sheetBackgroundColor = MaterialTheme.colorScheme.background,
        sheetContent = { BottomSheetContent(queueViewModel) },
        content = content
    )
}

@Composable
private fun QueueViewBody(
    queueViewModel: QueueViewModel
) {
    val videos = queueViewModel.videoQueue

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 10.dp),
    ) {
        if (videos.isEmpty()) {
            item {
                NoVideosOnQueue()
            }
        } else {
            items(count = videos.size) { index ->
                VideoItemComponent(
                    queueViewModel = queueViewModel,
                    video = videos[index]
                )
            }
        }
    }
}

@Composable
private fun NoVideosOnQueue() {
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

@ExperimentalMaterial3Api
@Composable
private fun BottomSheetContent(
    queueViewModel: QueueViewModel
) {
    var urlState by rememberSaveable { mutableStateOf("") }
    var isFieldEmpty by rememberSaveable { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Divider(
            thickness = 2.dp,
            modifier = Modifier
                .width(40.dp)
                .padding(5.dp),
        )

        TextFieldComponent(
            value = urlState,
            onError = isFieldEmpty,
            onValueChange = {
                urlState = it
                isFieldEmpty = it.isEmpty()
            },
            onErrorIcon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = stringResource(id = R.string.add_description)
                )
            },
            label = {
                Text(
                    "Video URL",
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (urlState.isNotEmpty()) {
                    queueViewModel.addVideo(Video(url = urlState))
                } else {
                    isFieldEmpty = true
                }
            },
        ) {
            Text("Add to queue")
        }
    }
}