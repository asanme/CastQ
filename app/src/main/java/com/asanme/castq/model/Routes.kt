package com.asanme.castq.model

sealed class Routes(val route: String) {
    object QueueViewRoute : Routes("queueRoute")
}