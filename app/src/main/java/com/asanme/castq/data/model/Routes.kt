package com.asanme.castq.data.model

sealed class Routes(val route: String) {
    object QueueViewRoute : Routes("queueRoute")
}